package com.douzon.mysite.action.guestbook;

import com.douzon.mvc.action.AbstractActionFactory;
import com.douzon.mvc.action.Action;
import com.douzon.mysite.action.main.IndexAction;

public class GuestBookActionFactory extends AbstractActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("guestbookform".equals(actionName)) {
			action = new GuestBookFormAction();
		}
		else if("insert".equals(actionName)) {
			action = new InsertAction();
		}
		else {
			action = new IndexAction();
		}
		
		return action;
	}

}
