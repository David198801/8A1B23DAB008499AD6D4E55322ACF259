把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。

输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。

NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。

非递减排序：arr[i]<=arr[i+1]



示例1

输入

[3,4,5,1,2]

返回值

1



二分查找

将mid与右端比较,mid = first + (last-first)/2

设原数组为

1~a,b~n

旋转后

b~n,1~a

可知1~a<=b~n

mid比较三种情况

1. arr[mid] > arr[last]

说明arr[mid]在左边，取右边继续运算，因为arr[mid]比arr[last]大，最小值不可能为arr[mid]，

所以first =mid + 1

2. arr[mid] < arr[last]

说明arr[mid]在右边，取左边继续运算，最小值可能为arr[mid]，所以last = mid

3. arr[mid] == arr[last]

arr[mid]可能在左边也可能在右边，last--，继续运算

111111111101

110111111111



```javascript
public int minNumberInarray(int [] array) {
	int l = array.length;
	if(l==0)
		return 0;
	int first = 0,last = l-1;
	while(first<last) {
		int mid = first + (last-first)/2;
		if(array[mid]>array[last]) {
			first = mid + 1;
		}else if(array[mid]>array[last]) {
			last = mid;
		}else {
			--last;
		}
	}
	return array[first];

}
```

