package com.sppl.transformer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Component;

import com.sppl.model.response.GetDraws;
import com.sppl.model.response.GetGames;
import com.sppl.model.response.RequestInfo ;
@Component
public class LotteryTransformer {
	public GetGames convertToLotteryGetGames(String channel) throws DatatypeConfigurationException {

		RequestInfo getGamesReqInfo = new RequestInfo();

		getGamesReqInfo.setChannel(channel);
		getGamesReqInfo.setVersion("1.0");

		GetGames getGamesReq = new GetGames();

		getGamesReq.setRequestInfo(getGamesReqInfo);

		return getGamesReq;

	}

	public GetDraws convertToLotteryGetDrawsDetail(String gameRef, String channel) throws DatatypeConfigurationException
			 {

		
		RequestInfo getDrawsReqInfo = new RequestInfo();

		getDrawsReqInfo.setChannel(channel);
		getDrawsReqInfo.setVersion("1.0");

		GetDraws.Data getDrawsData = new GetDraws.Data();

		getDrawsData.setGameRef(gameRef);
		getDrawsData.setStartDate(getStartDateXMLGregorianCalendar());
		getDrawsData.setEndDate(getEndtDateXMLGregorianCalendar());
		getDrawsData.setIsActive(false);

		GetDraws getDrawsReq = new GetDraws();

		getDrawsReq.setRequestInfo(getDrawsReqInfo);
		getDrawsReq.setData(getDrawsData);

		return getDrawsReq;

	}
	public XMLGregorianCalendar getStartDateXMLGregorianCalendar() throws DatatypeConfigurationException
			 {
		Calendar startcal = Calendar.getInstance();
		startcal.setTime(new Date());
		startcal.add(Calendar.MONTH, -2);
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		GregorianCalendar startDate = new GregorianCalendar();
		startDate.setTime(startcal.getTime());
		XMLGregorianCalendar greStartDate =
				DatatypeFactory.newInstance().newXMLGregorianCalendar(sdf.format(startDate.getTime()));

		return greStartDate;

	}
	public XMLGregorianCalendar getEndtDateXMLGregorianCalendar()
			throws DatatypeConfigurationException {

		Calendar endcal = Calendar.getInstance();
		endcal.setTime(new Date());
		endcal.add(Calendar.MONTH, 2);
		DateFormat sdfe = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

		// Here you say to java the initial timezone. This is the secret
		sdfe.setTimeZone(TimeZone.getTimeZone("GMT"));
		// Will print in UTC

		GregorianCalendar endDate = new GregorianCalendar();
		endDate.setTime(endcal.getTime());

		XMLGregorianCalendar greendDate =
				DatatypeFactory.newInstance().newXMLGregorianCalendar(sdfe.format(endcal.getTime()));

		return greendDate;

	}
}
