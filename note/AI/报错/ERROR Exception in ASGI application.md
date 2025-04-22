# 报错ERROR: Exception in ASGI application

ERROR:    Exception in ASGI application
Traceback (most recent call last):
  File "/home/wangzhefan/miniconda/envs/explain/lib/python3.12/site-packages/pydantic/type_adapter.py", line 270, in _init_core_attrs
    self._core_schema = _getattr_no_parents(self._type, '__pydantic_core_schema__')
                        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  File "/home/wangzhefan/miniconda/envs/explain/lib/python3.12/site-packages/pydantic/type_adapter.py", line 112, in _getattr_no_parents
    raise AttributeError(attribute)
AttributeError: __pydantic_core_schema

### 解决办法：更换一个gradio版本

```bat
pip install gradio --upgrade -i https://mirrors.cloud.tencent.com/pypi/simple
```