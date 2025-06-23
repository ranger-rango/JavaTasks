package main.java.com.habil.models;

public class Matter
{
    private StatesOfMatter state;
    private String objectName;
    public Matter(StatesOfMatter state, String objectName)
    {
        this.state = state;
        this.objectName = objectName;
    }

    public StatesOfMatter getState()
    {
        return state;
    }
    public String getObjectName()
    {
        return objectName;
    }

    public void setState(StatesOfMatter state)
    {
        this.state = state;
    }
    public void setObjectName(String objectName)
    {
        this.objectName = objectName;
    }

}
