package com.couzon.mysite.action.board;

import com.douzon.mvc.action.AbstractActionFactory;
import com.douzon.mvc.action.Action;
import com.douzon.mysite.action.main.IndexAction;

public class BoardActionFactory extends AbstractActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("boardform".equals(actionName)) {
			action = new BoardFormAction();
		}
		else if("viewform".equals(actionName)) {
			action = new ViewFormAction();
		}
		else if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		}
		else if("write".equals(actionName)) {
			action = new WriteAction();
		}
		else if("modifyform".equals(actionName)) {
			action = new ModifyFormAction();
		}
		else if("modify".equals(actionName)) {
			action = new ModifyAction();
		}
		else if("delete".equals(actionName)) {
			action = new DeleteAction();
		}
		else if("replyform".equals(actionName)) {
			action = new ReplyFormAction();
		}
		else if("reply".equals(actionName)) {
			action = new ReplyAction();
		}
		else if("search".equals(actionName)) {
			action = new SearchAction();
		}
		else if("comment".equals(actionName)) {
			action = new CommentAction();
		}
		else {
			action = new IndexAction();
		}
		
		return action;
	}

}
