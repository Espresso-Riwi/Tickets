package domain;

public class Comment {
    private int commentId;
    private int ticketId;
    private int userId;
    private String content;
    private String userName;

    public Comment(int commentId, int ticketId, int userId, String content) {
        setCommentId(commentId);
        setTicketId(ticketId);
        setUserId(userId);
        setContent(content);
    }

    public Comment(int ticketId, int userId, String content) {
        setTicketId(ticketId);
        setUserId(userId);
        setContent(content);
    }

    public Comment(int commentId, int ticketId, int userId, String content, String userName) {
        setCommentId(commentId);
        setTicketId(ticketId);
        setUserId(userId);
        setContent(content);
        setUserName(userName);
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
