

```javascript
import re


def is_count_odd(s, c):
    n = s.count(c)
    return n % 2 != 0


def is_pairs(s, c1, c2):
    return s.count(c1) == s.count(c2)


def parse_insert(sql):
    sql = sql.replace("\n","")
    fields_str = None
    values_str = None
    s = re.search(r'INSERT\s+INTO.+\((.*)\)\s*VALUES\s*\((.*)\)', sql, re.IGNORECASE)
    if s and len(s.groups()) >= 2:
        fields_str = s.group(1)
        values_str = s.group(2)

    fields = fields_str.split(",")
    fields = [x.strip() for x in fields]
    values = []

    # 处理括号包裹的逗号被分割的情况
    sp = values_str.split(",")
    flag = False
    count_left = 0
    count_right = 0
    for i in sp:
        trim = i.strip()
        if flag:
            count_left += trim.count("(")
            count_right += trim.count(")")
            if ")" in trim and count_left == count_right:
                flag = False
                count_left = 0
                count_right = 0
            values[-1] += "," + trim
        else:
            if "(" in trim and (not is_pairs(trim, "(", ")")):
                flag = True
                count_left += trim.count("(")
                count_right += trim.count(")")
            values.append(trim)

    # 处理单引号包裹的逗号被分割的情况
    sp = values
    values = []
    flag = False
    for i in sp:
        trim = i.strip()
        if flag:
            if (trim.endswith("'") and (not trim.startswith("'"))):
                flag = False
            values[-1] += "," + trim
        else:
            if trim.startswith("'") and (is_count_odd(trim, "'") or not trim.endswith("'")):
                flag = True
            values.append(trim)

    if fields and values and len(fields) == len(values):
        return {fields[i]: values[i] for i in range(len(fields))}
    else:
        print("未正确解析INSERT语句:")
        print("fields=", fields)
        print("values=", values)


if __name__ == '__main__':

    with open("sql.txt", "r", encoding="UTF-8") as txt:
        for sql in txt.readlines():
            if sql:
                print(sql[:-1])
                print(parse_insert(sql))
                print("=================")
```

