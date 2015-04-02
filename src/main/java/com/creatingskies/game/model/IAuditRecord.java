package com.creatingskies.game.model;

import java.util.Date;

public interface IAuditRecord extends IRecord{

	String getEntryBy();
	void setEntryBy(String entryBy);
	Date getEntryDate();
	void setEntryDate(Date entryDate);
	
	String getEditBy();
	void setEditBy(String editBy);
	Date getEditDate();
	void setEditDate(Date editDate);
}
