package com.sppl.model.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LotteryGame", propOrder = { "id", "text", "drawDays", "betTypes", "selectionGroups" })
@Data
public class LotteryGame {

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "lotteryBetType" })
	public static class BetTypes {

		@XmlElement(required = true)
		protected List<LotteryBetType> lotteryBetType;

		public List<LotteryBetType> getLotteryBetType() {
			if (lotteryBetType == null) {
				lotteryBetType = new ArrayList<LotteryBetType>();
			}
			return this.lotteryBetType;
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "drawDay" })
	public static class DrawDays {

		@XmlElement(required = true)
		protected List<String> drawDay;

		public List<String> getDrawDay() {
			if (drawDay == null) {
				drawDay = new ArrayList<String>();
			}
			return this.drawDay;
		}
	}

	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "selectionGroup" })
	public static class SelectionGroups {

		@XmlElement(required = true)
		protected List<SelectionGroup> selectionGroup;

		public List<SelectionGroup> getSelectionGroup() {
			if (selectionGroup == null) {
				selectionGroup = new ArrayList<SelectionGroup>();
			}
			return this.selectionGroup;
		}

	}

	protected LotteryGame.BetTypes betTypes;
	protected LotteryGame.DrawDays drawDays;

	@XmlElement(required = true)
	protected String id;

	protected LotteryGame.SelectionGroups selectionGroups;

	@XmlElement(required = true)
	protected String text;
}
