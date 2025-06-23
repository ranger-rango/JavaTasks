package main.java.com.habil.app;

interface Container<T>
{
   void set(T value);
   T get(); 
}



public class GenericsTutorial
{
    public static class Store<T> implements Container<T>
    {
        private T data;

        public void set(T data)
        {
            this.data = data;
        }

        public T get()
        {
            return data;
        }
    }

    public static void main(String[] args)
    {
        Store<String> stringStore = new Store<>();
        stringStore.set("Generic Class/Interface Example");
        System.out.println(stringStore.get());
    }
}

// generics enable classes, interfaces and methods to operate on objects of various types while 
// providing compile time safety 


