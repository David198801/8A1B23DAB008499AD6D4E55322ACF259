用 v-on:change。

不需要 directive 那么复杂，而且似乎 directive 也解决不了问题。

```javascript
<input type="file" @change="onChange">
```



```javascript
{
  ...
  methods: {
    onChange(event) {
      this.file = event.target.files[0];
    }
  }
}
```

