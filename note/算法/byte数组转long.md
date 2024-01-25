

```javascript
l=len(r)
for i in range(l):
    if i%4==0:
        n = 0
        for j in range(i,i+3+1):
            if j<l:
                n += r[j]<<((3-j%4)*8)
```

