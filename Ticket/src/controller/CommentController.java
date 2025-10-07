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

    public boolean createComment(int ticketId, String userDni, String content) {
        if (ticketId <= 0) {
            System.out.println("Error: Invalid ticket ID.");
            return false;
        }

        if (userDni == null || userDni.trim().isEmpty()) {
            System.out.println("Error: User DNI cannot be empty.");
            return false;
        }

        if (!Validator.isInteger(userDni)) {
            System.out.println("Error: Invalid DNI format.");
            return false;
        }

        if (content == null || content.trim().isEmpty()) {
            System.out.println("Error: Comment content cannot be empty.");
            return false;
        }

        if (content.trim().length() > 500) {
            System.out.println("Error: Comment content is too long (max 500 characters).");
            return false;
        }

        return commentService.createComment(ticketId, userDni.trim(), content.trim());
    }

    public boolean updateComment(int commentId, String newContent) {
        if (commentId <= 0) {
            System.out.println("Error: Invalid comment ID.");
            return false;
        }

        if (newContent == null || newContent.trim().isEmpty()) {
            System.out.println("Error: Comment content cannot be empty.");
            return false;
        }

        if (newContent.trim().length() > 500) {
            System.out.println("Error: Comment content is too long (max 500 characters).");
            return false;
        }

        return commentService.updateComment(commentId, newContent.trim());
    }

    public boolean deleteComment(int commentId) {
        if (commentId <= 0) {
            System.out.println("Error: Invalid comment ID.");
            return false;
        }
        return commentService.deleteComment(commentId);
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

    public void deleteCommentsByTicketId(int ticketId) {
        if (ticketId <= 0) {
            System.out.println("Error: Invalid ticket ID.");
            return;
        }
        commentService.deleteCommentsByTicketId(ticketId);
    }
}
