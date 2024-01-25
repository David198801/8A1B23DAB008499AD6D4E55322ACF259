:nth-child()代替eq，但两者不完全相同

| 偶数的元素 | :nth-child(even) | :even |
| - | - | - |
| 奇数的元素 | :nth-child(odd) | :odd |
| 大于给定索引值的元素 | :nth-child(n+2) | :gt(0) |
| 小于给定索引值的元素 | :nth-child(-1n+8) | :lt(2) |


