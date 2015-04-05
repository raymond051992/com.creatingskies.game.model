package com.creatingskies.game.model.type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.creatingskies.game.model.IRecord;

@Entity(name = "gGameType")
public class GameType implements IRecord {

	private static final long serialVersionUID = -3941347634467112060L;
	
	private Integer idNo;
	private String name;
	private byte[] icon;

	@Id
	@Override
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getIdNo() {
		return idNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public void setIdNo(Integer idNo) {
		this.idNo = idNo;
	}

}
