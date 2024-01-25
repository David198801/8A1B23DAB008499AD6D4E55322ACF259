mapper.xml未识别，检查mapper.xml注册

注意使用class属性，或使用<package>批量注册，需要mapper.xml和接口同名，并放在同一个包里

此时仍可获取到mapper的代理对象，但调用时会报错Invalid bound statement(not found)



检查mapper.xml命名空间



检查mapper.xml是否构建到target中