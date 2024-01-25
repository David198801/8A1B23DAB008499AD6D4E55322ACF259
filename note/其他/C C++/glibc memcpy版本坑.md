最早的 memcpy 是 copy forward，虽然所有 memcpy 的文档和约定都说不能用于 overlap 的场景，但是，copy forward 也可以安全地用在 src < dst 的 overlap 场景，但是中间 glibc 的 memcpy 改成了 copy backward，虽然仍符合 memcpy 的文档和约定，但是，原先 src < dst 的 overlap 场景，此时就挂了！但是 glibc 认为这不是bug。



linus 的意见是，虽然文档中说不能用于 overlap，但是以前一直是 copy forward，所以 src < dst 的 overlap 可以 work，把 copy forward 改成 copy backward 导致问题，这就是 bug。这一点上我是认同 linus 的。



但是 glibc 的解决方案太猥琐，把老版的 memcpy(也就是 memcpy@GLIBC_2.2.5) alias 到 memmove，然后增加了一个新版的 memcpy（ memcpy@GLIBC_2.14 ）。所以，新的 glibc 里面，memcpy@GLIBC_2.2.5 就是 memmove，比最早的 memcpy 能兼容更多错误用法（性能会略低）， memcpy@@GLIBC_2.14 就高性能版的 memcpy，不考虑 overlap，根据我最新的测试，它仍是 copy forward 的。



glibc 这个猥琐的解决方案确实解决了跟 flash 类似的那些 bug，但是引起了更恶心的问题，我们在新系统中编译出来的程序，因为使用了 memcpy@@GLIBC_2.14，就无法在旧系统上运行。所以，很多人（包括我们），只能被迫使用

__asm__(".symver memcpy,memcpy@GLIBC_2.2.5");

这两个问题，浪费的全世界的 developer hours，加起来不少于 300 人的生命时间。所以，glibc 的牛逼之处就在于，随便折腾一下，就可以杀死斯巴达 300 勇士。