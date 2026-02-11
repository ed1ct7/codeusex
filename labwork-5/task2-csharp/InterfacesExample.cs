using System;
using System.Collections.Generic;

namespace Интерфейсы
{
    interface IDrawable
    {
        void Draw();
    }

    interface IGeometrical
    {
        void GetPerimeter();
        void GetArea();
    }

    class Rectangle : IGeometrical, IDrawable
    {
        public void GetPerimeter()
        {
            Console.WriteLine("(a+b)*2");
        }

        public void GetArea()
        {
            Console.WriteLine("a*b");
        }

        public void Draw()
        {
            Console.WriteLine("Rectangle");
        }
    }

    class Circle : IGeometrical, IDrawable
    {
        public void GetPerimeter()
        {
            Console.WriteLine("2*pi*r");
        }

        public void GetArea()
        {
            Console.WriteLine("pi*r^2");
        }

        public void Draw()
        {
            Console.WriteLine("Circle");
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            List<IGeometrical> figures = new List<IGeometrical>();
            figures.Add(new Rectangle());
            figures.Add(new Circle());

            foreach (IGeometrical figure in figures)
            {
                figure.GetArea();
                figure.GetPerimeter();
            }

            foreach (IDrawable figure in figures)
            {
                figure.Draw();
            }

            Console.WriteLine("________________");

            foreach (IGeometrical figure in figures)
            {
                figure.GetArea();
                figure.GetPerimeter();

                Type myType = figure.GetType();
                Type[] list = myType.GetInterfaces();
                Console.WriteLine("Тип - " + myType);

                foreach (Type item in list)
                {
                    Console.WriteLine(item.ToString());
                }
            }

            Console.WriteLine("________________");

            foreach (IDrawable figure in figures)
            {
                figure.Draw();
            }
        }
    }
}
