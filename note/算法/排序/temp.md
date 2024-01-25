

```javascript
public static void mergeSort(int[] arr) {
    // 临时数组，用于每一次merge输出
    int[] temp = new int[arr.length];
    mergeSort(arr, temp, 0, arr.length - 1);
}

public static void mergeSort(int[] arr, int[] temp, int l, int r) {
    System.out.println(l+","+r);
    // 递归条件
    if (l < r) {
        int m = (l + r)/2;
        // 分治，两边排序。
        mergeSort(arr, temp, l, m-1);
        mergeSort(arr, temp, m, r);
        // 左右两边排序后merge
        merge(arr, temp, l, m, r);
    }
}

public static void merge(int[] arr, int[] temp, int l, int m, int r) {
    // 左右两边的指针
    int i = l, j = m ;
    // temp数组的指针
    int t = 0;
    // merge和分治处理的一样即可
    while (i <= m-1 && j <= r) {
        // 谁小temp数组就放谁，放完后两边自增1
        if (arr[i] < arr[j]) {
            temp[t++] = arr[i++];
        } else {
            temp[t++] = arr[j++];
        }
    }
    // 一边放完之后，另一边可能还有剩下的，两边已经排好序，直接放完即可
    while (i <= m-1) {
        temp[t++] = arr[i++];

    }
    while (j <= r) {
        temp[t++] = arr[j++];
    }
    // 每次merge完需要将temp中的数据写入到arr中
    t = 0;
    while (l <= r) {
        arr[l++] = temp[t++];
    }

}
```

