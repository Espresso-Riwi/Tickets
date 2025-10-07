package controller;

import service.CommentService;
import domain.Comment;
import util.Validator;
import java.util.List;

public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    public List<Comment> getCommentsByTicketId(int ticketId) {
        if (ticketId <= 0) {
            System.out.println("Error: Invalid ticket ID.");
            return null;
        }
        return commentService.getCommentsByTicketId(ticketId);
    }

    public Comment getCommentById(int commentId) {
        if (commentId <= 0) {
            System.out.println("Error: Invalid comment ID.");
            return null;
        }
        return commentService.getCommentById(commentId);
    }

    public String createComment(int ticketId, String userDni, String content) {
        if (ticketId <= 0) {
            return "Invalid ticket ID.";
        }

        if (userDni == null || userDni.trim().isEmpty()) {
            return "User DNI cannot be empty";
        }

        if (!Validator.isInteger(userDni)) {
            return "Invalid DNI format";
        }

        if (content == null || content.trim().isEmpty()) {
            return "Comment content cannot be empty";
        }

        if (content.trim().length() > 500) {
            return "Comment content is too long (max 500 characters).";
        }

        return commentService.createComment(ticketId, userDni.trim(), content.trim());
    }



    public boolean canUserModifyComment(int commentId, String userDni) {
        if (commentId <= 0) {
            return false;
        }

        if (userDni == null || userDni.trim().isEmpty()) {
            return false;
        }

        if (!Validator.isInteger(userDni)) {
            return false;
        }

        return commentService.canUserModifyComment(commentId, userDni.trim());
    }

}
