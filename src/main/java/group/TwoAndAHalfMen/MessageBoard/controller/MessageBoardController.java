package group.TwoAndAHalfMen.MessageBoard.controller;

import group.TwoAndAHalfMen.MessageBoard.data.IDatabase;
import group.TwoAndAHalfMen.MessageBoard.data.SQLiteDatabase;
import group.TwoAndAHalfMen.MessageBoard.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")

public class MessageBoardController {

    //Initalize messageList;
    private List<Message> messageList;
    //This method is called automatically by Spring to initialize the data
    @PostConstruct
    public void init() {
        // Read message from database
        IDatabase database=new SQLiteDatabase("messageBoard.sqlite");
        messageList=database.readMessage();
        database.closeConnection();
    }

    @RequestMapping(value="/", method= RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        //mv.getModel().put("message", "Hello World!");
        return mv;
    }


    //list the message on the board.
    @RequestMapping(value="/list", method= RequestMethod.GET)
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.getModel().put("messages", messageList);
        return mv;
    }
    //return add.html view
    @RequestMapping(value="/add", method= RequestMethod.GET)
    public String addForm(){
        return "add";
    }

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public String save(@RequestParam Map<String, String> allParams){
        String title = allParams.get("title");
        String content=allParams.get("content");
        String sender=allParams.get("sender");
        String urlAddress=allParams.get("urlAddress");
        Message message = new Message(title, content, sender,urlAddress);
        messageList.add(message);
        // Save message to databse
        IDatabase database=new SQLiteDatabase("messageBoard.sqlite");
        database.addMessage(message);
        database.closeConnection();

        return "redirect:/list";
    }
    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String search(@RequestParam Map<String,String> allParams){
        String title=allParams.get("title");
        String content=allParams.get("content");
        String sender="";
        String urlAddress="";

        Message message=new Message(title,content,sender,urlAddress);
        IDatabase database=new SQLiteDatabase("messageBoard.sqlite");
        messageList= database.searchMessage(message);
        database.closeConnection();

        return "redirect:/list";

    }
    @RequestMapping(value="/change", method= RequestMethod.GET)
    public String changeForm(){
        return "change";
    }
    @RequestMapping(value="/savechange", method= RequestMethod.POST)
    public String saveChange(@RequestParam Map<String, String> allParams){
        String title = allParams.get("title");
        String content=allParams.get("content");
        String sender=allParams.get("sender");
        String urlAddress=allParams.get("urlAddress");
        Message message = new Message(title, content, sender,urlAddress);
        // Save message to databse
        IDatabase database=new SQLiteDatabase("messageBoard.sqlite");
        messageList= database.changeMessage(message);
        database.closeConnection();

        return "redirect:/list";
    }
}
