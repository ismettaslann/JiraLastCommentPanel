package com.example.plugins.tutorial;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.comments.Comment;
import com.atlassian.jira.issue.comments.CommentManager;
import com.atlassian.jira.plugin.webfragment.contextproviders.AbstractJiraContextProvider;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;

import java.util.*;

public class LastCommentViewer extends AbstractJiraContextProvider {

    @Override
    public Map getContextMap(ApplicationUser applicationUser, JiraHelper jiraHelper) {
        Map<String, Object> contextMap = new HashMap<>();
        Issue currentIssue = (Issue) jiraHelper.getContextParams().get("issue");

        /**
         * ChangeHistoryManager ile tüm metodları gezmeme rağmen son AKTİF yorumu bulamadım.
         * ChangeHistoryManager ile gelen comment field ile gelen tüm değerler silinmiş commentlerdi.
         * Issue üzerindeki editlediğim commentler de froms ve tos bilgileri içinde gelmemekte.
         * Aşağıdaki linkler de beni CommentManager kullanmaya itti. Projeyi göndermemek yerine bu şekilde göndermek istedim.
         * https://community.atlassian.com/t5/Jira-questions/Comments-in-Issue-Updated-Event/qaq-p/64862
         * https://community.atlassian.com/t5/Jira-questions/Comment-edits-are-not-shown-in-History/qaq-p/129193
         * https://community.atlassian.com/t5/Answers-Developer-Questions/how-retrieve-all-comments-from-an-issue/qaq-p/471319
        */

        CommentManager commentManager = ComponentAccessor.getCommentManager();
        Comment comment = commentManager.getLastComment(currentIssue);
        contextMap.put("lastCommit", comment == null ? "" : comment.getBody());
        return contextMap;
    }
}
