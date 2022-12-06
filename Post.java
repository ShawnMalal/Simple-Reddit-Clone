
/**
 * Name: Shawn Malal
 * Email: smalal@ucsd.edu
 * PID: A127420444
 * Sources used: JDK documentation, Lecture notes
 * 
 * This file is used to create an object which is a reddit-like post.
 * There are necessary methods and contstructors to edit things like 
 * the number of upvotes on the post, downvotes on the post, an entire
 * thread of posts and comments, etc.
 */

//These are the imports
import java.util.ArrayList;

/**
 * This class is for the object "Post" and to create all the respective
 * methods and constructors for this object that can and will be used 
 * in the user file as long as an object is created.  
 */
public class Post {
    
    //created instance variables
    private String title;
    private String content;
    private Post replyTo;
    private User author;
    private int upvoteCount;
    private int downvoteCount;
    private static final String TO_STRING_POST_FORMAT = "[%d|%d]\t%s\n\t%s";
    private static final String TO_STRING_COMMENT_FORMAT = "[%d|%d]\t%s";
   
    /**
     * constructor for a post 
     * @param title title of the post
     * @param content the content of the post
     * @param author the one who made the post 
     */
    public Post(String title, String content, User author) { 
        upvoteCount = 1;
        downvoteCount = 0;
        this.title = title;
        this.content = content;
        this.author = author;

    }

    /**
     * constructor for a comment and set title to null
     * @param content the content of a comment
     * @param replyTo the post it is replying to
     * @param author the one who made the comment 
     */
    public Post(String content, Post replyTo, User author) { 
        upvoteCount = 1;
        downvoteCount = 0;
        this.title = null;
        this.content = content;
        this.author = author;
        this.replyTo = replyTo;
    }

    /**
     * getter for the title
     * @return the title of the post 
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * getter for the replyTo
     * @return the post that is being replied to
     */
    public Post getReplyTo() { 
        return this.replyTo; 
    }

    /**
     * getter for the author name
     * @return the name of the author of the post
     */
    public User getAuthor() { 
        return this.author;
    }

    /**
     * getter for the upvote count
     * @return the upvote count of the post 
     */
    public int getUpvoteCount() { 
        return this.upvoteCount;
    }
    
    /**
     * getter for the downvote count 
     * @return the downvote count of the post 
     */
    public int getDownvoteCount() { 
        return this.downvoteCount;
    }

    /**
     * adding 1 to upvote count if paramter is true, 
     * subtracting 1 from the upvote count
     * @param isIncrement true or false 
     */
    public void updateUpvoteCount(boolean isIncrement) {
        
        if (isIncrement == true) { 
            upvoteCount++;
        }
        
        else {
            upvoteCount--;
        }

    }

      /**
     * adding 1 to downvote count if paramter is true, 
     * subtracting 1 from the downvote count
     * @param isIncrement true or false 
     */
    public void updateDownvoteCount(boolean isIncrement){

        if (isIncrement == true) {
            downvoteCount++;
        }

        else {
            downvoteCount--;
        }

    }

    /**
     * getter for a thread of posts 
     * @return the thread
     */
    public ArrayList<Post> getThread() { 

        ArrayList <Post> temp = new ArrayList<Post>();
        ArrayList <Post> thread = new ArrayList<Post>();
        
        Post x = this;

        while (x != null) { 
            temp.add(x);
            x = x.getReplyTo();
        }

        for (int i = temp.size() - 1; i >= 0; i--){ 
            thread.add(temp.get(i));
        }

        return thread;

    }

    /**
     * formatter for the Post and comment
     */
    public String toString() {

        if (this.title == null) {
            String commentFormat = String.format(TO_STRING_COMMENT_FORMAT,
            upvoteCount, downvoteCount, content);
            return commentFormat;
        }

        else { 
            String postFormat = String.format(TO_STRING_POST_FORMAT,
            upvoteCount, downvoteCount, this.title, content);
            return postFormat;
        }

    }


}
