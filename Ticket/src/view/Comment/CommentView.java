package view.Comment;

import controller.CommentController;
import domain.Comment;
import view.ViewMessages;

import java.util.List;

public class CommentView {
    private CommentController commentController;

    public CommentView(CommentController commentController) {
        this.commentController = commentController;
    }

    public void showCommentsByTicketInput() {
        String ticketIdStr = ViewMessages.showQuestionMessage("Enter ticket ID:", "Ticket ID");
        try {
            int ticketId = Integer.parseInt(ticketIdStr);
            showCommentsByTicket(ticketId);
        } catch (NumberFormatException e) {
            ViewMessages.showInfoMessage("Invalid ticket ID.", "Error");
        }
    }

    public void showCommentsByTicket(int ticketId) {
        List<Comment> comments = commentController.getCommentsByTicketId(ticketId);
        String message = "";

        if (comments == null || comments.isEmpty()) {
            message = "This ticket has no comments yet.";
        } else {
            message = "Comments for Ticket #" + ticketId + ":\n\n";
            for (Comment comment : comments) {
                message += "ID: " + comment.getCommentId() + "\n";
                message += "User: " + comment.getUserName() + "\n";
                message += "Content: " + comment.getContent() + "\n\n";
            }
        }
        ViewMessages.showInfoMessage(message, "Ticket Comments");
    }

    public void createCommentInput() {
        String ticketIdStr = ViewMessages.showQuestionMessage("Enter ticket ID:", "Create Comment");
        String userDni = ViewMessages.showQuestionMessage("Enter your DNI:", "Create Comment");
        String content = ViewMessages.showQuestionMessage("Enter comment content:", "Create Comment");

        try {
            int ticketId = Integer.parseInt(ticketIdStr);
            boolean success = commentController.createComment(ticketId, userDni, content);
            if (success) {
                ViewMessages.showInfoMessage("Comment created successfully.", "Success");
            } else {
                ViewMessages.showInfoMessage("Could not create comment. Please verify the data.", "Error");
            }
        } catch (NumberFormatException e) {
            ViewMessages.showInfoMessage("Invalid ticket ID.", "Error");
        }
    }


    public void showAllComments() {
        List<Comment> comments = commentController.getAllComments();
        String message = "";

        if (comments == null || comments.isEmpty()) {
            message = "No comments in the system.";
        } else {
            message = "All Comments:\n\n";
            for (Comment comment : comments) {
                message += "ID: " + comment.getCommentId() + " | Ticket: #" + comment.getTicketId() + "\n";
                message += "User: " + comment.getUserName() + "\n";
                message += "Content: " + comment.getContent() + "\n\n";
            }
        }
        ViewMessages.showInfoMessage(message, "All Comments");
    }
}
