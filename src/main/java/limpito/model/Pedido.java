package limpito.model;
import java.util.Date;

public class Pedido {

  public Integer id;
  public String name;
  public String assignee;
  public Date createTime;
  public Boolean suspended;

  public Pedido(Integer id,String name,String assignee,Date createTime,Boolean suspended){
    this.id = id;
    this.name = name;
    this.assignee = assignee;
    this.createTime = createTime;
    this.suspended = suspended;
  }

  @Override
  public String toString(){
    return "id: "+ id.toString() +"\n"
          +"name: "+ name +"\n"
          +"assignee: "+ assignee +"\n"
          +"createTime: "+ createTime.toString() +"\n"
          +"suspended: "+ suspended.toString() +"\n";
  }



}