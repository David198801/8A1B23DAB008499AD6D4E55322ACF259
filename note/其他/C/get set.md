

```javascript
using System;

namespace HelloWorld
{
    class Program
    {
        static void Main(string[] args)
        {
            MyClass mc = new MyClass();
            mc.Id = -2;
            mc.Name = "aaa";
            Console.WriteLine(mc.Id);//0
        }
    }

    class MyClass
    {
        private int id;
        private string name;

        public int Id
        {
            get => id;
            set
            {
                if (value < 0)
                {
                    id = 0;
                }
                else
                {
                    id = value;
                }
            }
        }
        public string Name { get => name; set => name = value; }
    }
}

```

自动生成快捷键

ctrl+R,ctrl+E

ctr+r+e:按住CTR，然后按下R ，再按E