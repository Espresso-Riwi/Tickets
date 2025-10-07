package view.Comment;

import javax.swing.*;

public class CommentMenu {
    private CommentView commentView;

    public CommentMenu(CommentView commentView) {
        this.commentView = commentView;
    }

    public void commentMenu() {
        String[] options = {"All comments", "Comments by ticket", "Create comment", "Update comment", "Delete comment"};
        String option = JOptionPane.showInputDialog(null, "Choose an option", "Comment menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();

        switch (option) {
            case "All comments":
                commentView.showAllComments();
                break;
            case "Comments by ticket":
                commentView.showCommentsByTicketInput();
                break;
            case "Create comment":
                commentView.createCommentInput();
                break;
            case "Update comment":
                commentView.updateComment();
                break;
            case "Delete comment":
                commentView.deleteComment();
                break;
        }
    }
}
