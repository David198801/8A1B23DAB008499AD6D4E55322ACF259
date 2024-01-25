https://segmentfault.com/q/1010000040992340?utm_source=sf-homepage



```javascript
[
    {key:'alan',value:12},
    {key:'mike',value:18}
]
```

期望值

```javascript
{alan:12,mike:18}
```



1.

```javascript
const data = [
    { key: "alan", value: 12 },
    { key: "mike", value: 18 }
];

// 就这句话，换行主要是为了看清楚，可以不换
const result = Object.fromEntries(
    data.map(({ key, value }) => [key, value])
);

console.log(result);
```

2.

```javascript
[{key:'alan',value:12},{key:'mike',value:18}].reduce((res, o) => (res[o.key] = o.value, res), {})
```

