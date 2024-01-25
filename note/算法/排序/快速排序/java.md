1.递归版

```javascript
public static void quickSort(int[] arr, int l, int r) {
	// 递归条件
	if (l < r) {
		int i = l, j = r;
		int x = arr[i];
		while (i < j) {
			while (i < j && arr[j] >= x)
				j--;
			if (i < j) {
				arr[i] = arr[j];
				i++;
			}
			while (i < j && arr[i] < x)
				i++;
			if (i < j) {
				arr[j] = arr[i];
				j--;
			}
		}
		arr[i] = x;
		quickSort(arr, l, i - 1);
		quickSort(arr, i + 1, r);
	}
}
```



```javascript
public static void quickSort(int[] arr) {
    quickSort(arr, 0, arr.length - 1);
}

public static void quickSort(int[] arr, int l, int r) {
    // 递归条件
    if (l < r) {
        int pivotIndex = quickSortPartition(arr, l, r);
        quickSort(arr, l, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, r);
    }
}

public static int quickSortPartition(int[] arr,int l,int r){
    int pivot = arr[l];
    while (l < r) {
        while (l < r && arr[r] >= pivot)
            r--;
        if (l < r) {
            arr[l] = arr[r];
            l++;
        }
        while (l < r && arr[l] < pivot)
            l++;
        if (l < r) {
            arr[r] = arr[l];
            r--;
        }
    }
    arr[l] = pivot;
    return l;
}
```

2.循环版

```javascript

```



分解

```javascript
public static void main(String[] args) {
	int[] arr = new int[] { 62, 95, 84, 100, 54, 61, 78, 0, 1 };
	int x = dig(arr, 0, arr.length - 1);
	System.out.println(x);
	System.out.println(Arrays.toString(arr));

	recursion(arr, 0, arr.length - 1);
	System.out.println(Arrays.toString(arr));

	System.out.println("---------quick sort--------");
	int[] arr2 = new int[] { 62, 95, 84, 100, 54, 61, 78, 0, 1 };
	quickSort(arr2, 0, arr2.length - 1);
	System.out.println(Arrays.toString(arr2));
	int[] arr3 = new int[] { 62, 95, 88, 84, 100, 12, 15, 62, 95, 84, 100,
			54, 61, 78, 0, 1, 1, 2, 3, 3, 3, 100, 100, 62, 84, 12, 15, 21,
			50, 900, 1230, 55, 77 };
	quickSort(arr3, 0, arr3.length - 1);
	System.out.println(Arrays.toString(arr3));

}

// 快排可分为挖坑填数和递归两部分

// 挖坑填数：将大于基准数的放到右边，小于基准数的放左边，基准数放中间
// 返回调整后基准数的位置
public static int dig(int[] arr, int l, int r) {
	int i = l, j = r;
	// 取第一个元素为基准数x
	int x = arr[i];
	while (i < j) {
		// 每个判断都对i/j进行操作，故每一步都检测i<j，若不符合就说明i==j，已经得出结果
		// 从右向左找小于x的数来填s[i]
		while (i < j && arr[j] >= x)
			j--;
		if (i < j) {
			arr[i] = arr[j]; // 将s[j]填到s[i]中，s[j]就形成了一个新的坑
			i++;
		}

		// 从左向右找大于或等于x的数来填s[j]
		while (i < j && arr[i] < x)
			i++;
		if (i < j) {
			arr[j] = arr[i]; // 将s[i]填到s[j]中，s[i]就形成了一个新的坑
			j--;
		}
	}
	// i==j时，基准数赋给中间
	arr[i] = x;
	return i;
}

// 递归
public static void recursion(int[] arr, int l, int r) {
	if (l < r) {
		// 调用挖坑填数，获取基准数下标i
		int i = dig(arr, l, r);
		// 递归处理左边(l~i-1)和右边(i+1~r)部分
		recursion(arr, l, i - 1);
		recursion(arr, i + 1, r);
	}
}
```

比较时>=写不写或写在哪边不影响

分析：

若不写或两个都写，则左右都可能会有等于基准数的元素

设本次基准数为x，下一次左边的基准数为xl

若x不是最小值，则有小于x的值被移到首位，xl<x，故等于x的值会被移到右边，依次类推直到等于x的值为首位，则小于x的值会被移到左边

若x为最小值，则本次排序只确定x(首位)，依次类推直到首位不是最小值

依次类推等于x的值全部被移到最右边

同理右边的等于x的值全部被移到最左边