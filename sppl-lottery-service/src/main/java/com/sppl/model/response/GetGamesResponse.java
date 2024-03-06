package com.sppl.model.response;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responseInfo",
    "lotteryGames"
})
@XmlRootElement(name = "GetGamesResponse")
public class GetGamesResponse {

	
	 @XmlAccessorType(XmlAccessType.FIELD)
	    @XmlType(name = "", propOrder = {
	        "lotteryGame"
	    })
	    public static class LotteryGames {

	        @XmlElement(required = true)
	        protected List<LotteryGame> lotteryGame;

			public List<LotteryGame> getLotteryGame() {
				
				return lotteryGame;
			}

		
}
	 
	 protected GetGamesResponse.LotteryGames lotteryGames;

	    @XmlElement(required = true)
	    protected ResponseInfo responseInfo;

		public GetGamesResponse.LotteryGames getLotteryGames() {
			return lotteryGames;
		}

		public void setLotteryGames(GetGamesResponse.LotteryGames lotteryGames) {
			this.lotteryGames = lotteryGames;
		}

		public ResponseInfo getResponseInfo() {
			return responseInfo;
		}

		public void setResponseInfo(ResponseInfo responseInfo) {
			this.responseInfo = responseInfo;
		}

}