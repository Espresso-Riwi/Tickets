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

    public void updateComment() {
        String commentIdStr = ViewMessages.showQuestionMessage("Enter comment ID to update:", "Update Comment");

        try {
            int commentId = Integer.parseInt(commentIdStr);
            Comment comment = commentController.getCommentById(commentId);

            if (comment == null) {
                ViewMessages.showInfoMessage("Comment does not exist.", "Error");
                return;
            }

            String userDni = ViewMessages.showQuestionMessage("Enter your DNI to verify ownership:", "Update Comment");

            if (!commentController.canUserModifyComment(commentId, userDni)) {
                ViewMessages.showInfoMessage("You do not have permission to modify this comment.", "Error");
                return;
            }

            String newContent = ViewMessages.showQuestionMessage("Enter new content:", "Update Comment");
            boolean success = commentController.updateComment(commentId, newContent);

            if (success) {
                ViewMessages.showInfoMessage("Comment updated successfully.", "Success");
            } else {
                ViewMessages.showInfoMessage("Could not update comment.", "Error");
            }
        } catch (NumberFormatException e) {
            ViewMessages.showInfoMessage("Invalid comment ID.", "Error");
        }
    }

    public void deleteComment() {
        String commentIdStr = ViewMessages.showQuestionMessage("Enter comment ID to delete:", "Delete Comment");

        try {
            int commentId = Integer.parseInt(commentIdStr);
            Comment comment = commentController.getCommentById(commentId);

            if (comment == null) {
                ViewMessages.showInfoMessage("Comment does not exist.", "Error");
                return;
            }

            String userDni = ViewMessages.showQuestionMessage("Enter your DNI to verify ownership:", "Delete Comment");

            if (!commentController.canUserModifyComment(commentId, userDni)) {
                ViewMessages.showInfoMessage("You do not have permission to delete this comment.", "Error");
                return;
            }

            String confirmation = ViewMessages.showQuestionMessage("Are you sure you want to delete this comment? (yes/no):", "Delete Comment");

            if (confirmation != null && (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y"))) {
                boolean success = commentController.deleteComment(commentId);
                if (success) {
                    ViewMessages.showInfoMessage("Comment deleted successfully.", "Success");
                } else {
                    ViewMessages.showInfoMessage("Could not delete comment.", "Error");
                }
            } else {
                ViewMessages.showInfoMessage("Operation cancelled.", "Information");
            }
        } catch (NumberFormatException e) {
            ViewMessages.showInfoMessage("Invalid comment ID.", "Error");
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
