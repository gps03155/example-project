package com.couzon.mysite.action.board;

import com.douzon.mvc.action.AbstractActionFactory;
import com.douzon.mvc.action.Action;

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
		return action;
	}

}
