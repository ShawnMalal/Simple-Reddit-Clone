/**
 * Name: Shawn Malal
 * Email: smalal@ucsd.edu
 * PID: A127420444
 * Sources used: JDK documentation, Lecture notes
 * 
 * This file is used to edit things for the user, such as 
 * adding posts, editing their karma, upvoting and downvoting
 * their posts, etc. 
 */

//These are the imports
import java.util.ArrayList;

/**
 * This class is for the object "User" and to create all the respective
 * methods and constructors for this object 
 */
public class User {

    //Created instance variables
    private int karma;
    private String username;
    private ArrayList<Post> posts;
    private ArrayList<Post> upvoted;
    private ArrayList<Post> downvoted;
    private static final String TO_STRING_USER_FORMAT = "u/%s Karma: %d";
    

    /**
     * constructor for getting the username of the User
     * @param username of the User
     */
    public User(String username) { 
        
        this.username = username; 
        karma = 0; 

        posts = new ArrayList<Post>();
        upvoted = new ArrayList<Post>();
        downvoted = new ArrayList<Post>();


    }

    /**
     * adding a post to the end of the posts array list
     * @param post the post being added 
     */
    public void addPost(Post post) { 

        if (post == null) { 
            return; 
        }


        this.posts.add(post);

        updateKarma();

    }

    /**
     * updating the karma of the current user 
     */
    public void updateKarma() { 

        for (int i = 0; i < posts.size(); i++) {
            
            int upvoteCount = posts.get(i).getUpvoteCount();
            int downvoteCount = posts.get(i).getDownvoteCount();
            karma += upvoteCount - downvoteCount; 

        }

    }

    /**
     * getter for the karma value of the user 
     * @return karma int value of user 
     */
    public int getKarma() { 
        return karma;
    }

    /**
     * method for a user to upvote a post
     * @param post the post being upvoted
     */
    public void upvote(Post post) { 

        User author = post.getAuthor();

        if (post.equals(null)) { 
            return;
        }

        if (upvoted.contains(post) || author.username == this.username) { 
            return;
        }

        if (downvoted.contains(post)) { 
            downvoted.remove(post);
            post.updateDownvoteCount(false);
        }

        upvoted.add(post);
        post.updateUpvoteCount(true);
        
        author.updateKarma();
    }

    /**
     * method for a user to downvote a post 
     * @param post the post being downvoted
     */
    public void downvote(Post post) { 
        
        User author = post.getAuthor();

        if (post.equals(null)) { 
            return;
        }

        if (downvoted.contains(post) || author.username == this.username) { 
            return;
        }

        if (upvoted.contains(post)) { 
            upvoted.remove(post);
            post.updateUpvoteCount(false);
        }

        downvoted.add(post);
        post.updateDownvoteCount(true);

        author.updateKarma();

    }

    /**
     * getter for the post with the highest karma value
     * @return return type of Post and is the Top Post with highest karma
     */
    public Post getTopPost() { 
        
        Post topPost = null;
        int firstIndex = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < posts.size(); i++) {

            if (posts.get(i).getTitle() != null) { 
                topPost = posts.get(i);
                int upvotes = posts.get(i).getUpvoteCount();
                int downvotes = posts.get(i).getDownvoteCount();
                max = upvotes - downvotes;
                firstIndex = i;
                break;
            }
            
        }
        
        int karmaOfPost;

        for (int i = firstIndex + 1; i < posts.size(); i++) { 

            if (posts.get(i).getTitle() == null) { 
                continue;
            }
            
            int upvotes = posts.get(i).getUpvoteCount();
            int downvotes = posts.get(i).getDownvoteCount();
            karmaOfPost = upvotes - downvotes; 

            if (max < karmaOfPost) { 
                topPost = posts.get(i);
                max = karmaOfPost;
            }

        }
        
        return topPost;

    }

    /**
     * getter for the Comment with the highest karma value
     * @return of type Post and is the Top Comment with highest karma
     */
    public Post getTopComment() { 

        Post topComment = null;
        int firstIndex = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < posts.size(); i++) {

            if (posts.get(i).getTitle() == null) { 
                topComment = posts.get(i);
                int upvotes = posts.get(i).getUpvoteCount();
                int downvotes = posts.get(i).getDownvoteCount();
                max = upvotes - downvotes;
                firstIndex = i;
                break;
            }
            
        }
        
        int karmaOfPost;

        for (int i = firstIndex + 1; i < posts.size(); i++) { 

            if (posts.get(i).getTitle() != null) { 
                continue;
            }
            
            int upvotes = posts.get(i).getUpvoteCount();
            int downvotes = posts.get(i).getDownvoteCount();
            karmaOfPost = upvotes - downvotes; 

            if (max < karmaOfPost) { 
                topComment = posts.get(i);
                max = karmaOfPost;
            }

        }

        return topComment;

    }


    /**
     * getter for all the posts of a User
     * @return an arrayList of the posts of the user
     */
    public ArrayList<Post> getPosts() { 
        return this.posts;
    }

    /**
     * formatter for the User
     */
    public String toString() { 

        String userFormat = String.format
        (TO_STRING_USER_FORMAT, username, karma);

        return userFormat;

    }
}
