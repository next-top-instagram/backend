package com.spring.instagram.comment;

public class Comment {

    private Long id;
    private String name;
    private String commentStr;

    public Comment(Long id, String name,String commentStr){
        this.id = id;
        this.name = name;
        this.commentStr = commentStr;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }


   public String getName()
   {
       return name;
   }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommentStr()
    {
        return commentStr;
    }

    public void setCommentStr(String commentStr)
    {
        this.commentStr = commentStr;
    }



    @Override
    public String toString()
    {
        return "Comment{" + "id=" + id + ", name='" + name +
                '\'' + ", commentStr='" + commentStr + '}';
    }

}
