def isAscii(c):
    return ord(c) < 128
    # return len(c) == len(c.encode())

def getUnicodeCharacterUnicode(u):
    return str(u.encode("unicode_escape"),"ascii")
#str(b'xxx',ascii)可将b'xxx'转为'xxx'

def getAsciiCharacterUnicode(c):
    return hex(ord(c)).replace("0x",r"\u00")

def getStringUnicode(s):
    sSum = ""
    for c in s:
        if isAscii(c):
            p = getAsciiCharacterUnicode(c)
        else:
            p = getUnicodeCharacterUnicode(c)
        sSum += p
    return sSum


def getUnicodeCharacterUtf8(u):
    return str(u.encode("utf8"))[2:-1]

def getAsciiCharacterUtf8(c):
    return r"\x" + hex(ord(c))[2:]

def getStringUtf8(s):
    sSum = ""
    for c in s:
        if isAscii(c):
            p = getAsciiCharacterUtf8(c)
        else:
            p = getUnicodeCharacterUtf8(c)
        sSum += p
    return sSum

print(getStringUnicode("你好1122b我好"))
print(getStringUtf8("你好1122b我好"))


# def replaceAsciiInAllUtf8(u):
#     uDivide = u.split("\\")[1:]
#     for i in range(len(uDivide)):
#         num16 = "0"+uDivide[i]
#         num10 = int(num16,16)
#         if num10<128:
#             uDivide[i] = chr(num10)
#         else:
#             uDivide[i] = "\\" + uDivide[i]
#     return "".join(uDivide)