package com.cetc.iot.accesssystem.downinterface.socket.commmunication;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;

import net.sf.json.JSONObject;

import com.cetc.iot.accesssystem.downinterface.MsgOperation;
import com.cetc.iot.accesssystem.upinterface.PEData;
import com.cetc.iot.accesssystem.upinterface.UpperOperation;
import com.cetc.iot.communicate.PESession;
import com.cetc.iot.util.GetData;
import com.google.common.collect.Multimap;

/**
 * this class is used to monitor the status the system works.
 * this TCP Server is based on netty structure
 * @author xzc
 * Create Time: 2014-12-09
 * Author: xzc
 * Details: add read function for message reading handler
 *          add sendInfo function for message sending handler
 *
 */
public class MonitorSocketUtil {
	
	private static int x = 50;
	private static int y = 50;
	
	public static void read(String msg,ChannelHandlerContext ctx){
		String resp = "";
		if (msg.equalsIgnoreCase("help")){
			resp = "Press 1 for TCP server details.\n"
				  //+"Press 2 for UDP server details.\n"
				  +"Press 3 for data details.\n"
				  +"Press 4 for power temperature control.\n"
				  +"Press 5 for temperature control.\n"
				  +"Press 6 for car control.\n"
				  +"Press 7 for log get.";
		}
		else if (msg.equalsIgnoreCase("1")){
			resp = "Press 11 for PeID_ChannelHandlerContext map details.\n"
				  +"Press 12 for ChannelHandlerContext_PeID multi map details.";
		}
//		else if (msg.equalsIgnoreCase("help 2")){
//			resp = "Press 21";
//		}
		else if (msg.equalsIgnoreCase("3")){
			resp = "Press 31 for PeID_GetDataObject multi map details.\n"
				  +"Press 32 for PeID_MutexObject map details.";
		}
		else if (msg.equalsIgnoreCase("4")){
			resp = "Press 41 for send control on message to power temperature sensor.\n"
					+"Press 42 for send control off message to power temperature sensor.\n"
					+"Press 43 for send control get message to power temperature sensor.";
		}
		else if (msg.equalsIgnoreCase("5")){
			resp = "Press 51 for send control on message to temperature sensor.\n"
					+"Press 52 for send control off message to temperature sensor.\n"
					+"Press 53 for send control get message to temperature sensor.";
		}
		else if (msg.equalsIgnoreCase("6")){
			resp = "Press 61 for send forward message to car.\n"
					+"Press 62 for send backward message to car.\n"
					+"Press 63 for send left message to car.\n"
					+"Press 64 for send right message to car.\n"
					+"Press 65 for send stop message to car.\n"
					+"Press 66 for send cam_x_up message to car.\n"
					+"Press 67 for send cam_x_down message to car.\n"
					+"Press 68 for send cam_y_up message to car.\n"
					+"Press 69 for send cam_y_down message to car.";
		}
		else if (msg.equalsIgnoreCase("7")){
			resp = "Press 7#filename to get log file.";
		}
		else if (msg.equalsIgnoreCase("11")){
			ReadWriteLock lock = ServerUtil.getLock();
			lock.readLock().lock();
			Map<String,ChannelHandlerContext> map = ServerUtil.getServerPEIDChannelHandlerContextMap();
			resp = ""+map;
			lock.readLock().unlock();
		}
		else if (msg.equalsIgnoreCase("12")){
			ReadWriteLock lock = ServerUtil.getLock();
			lock.readLock().lock();
			Multimap<ChannelHandlerContext, String> multiMap = ServerUtil.getServerChannelHandlerContextPEIDMap();
			resp = ""+multiMap;
			lock.readLock().unlock();
		}
		else if (msg.equalsIgnoreCase("31")){
			ReadWriteLock lock = UpperOperation.getLock();
			lock.readLock().lock();
			Multimap<String, GetData> multiMap = UpperOperation.getPEIDGetDataObjectMap();
			resp = ""+multiMap;
			lock.readLock().unlock();
		}
		else if (msg.equalsIgnoreCase("32")){
			ReadWriteLock lock = UpperOperation.getLock();
			lock.readLock().lock();
			ConcurrentHashMap<String, Object> map = UpperOperation.getPEIDMutexObjectMap();
			resp = ""+map;
			lock.readLock().unlock();
		}
		else if (msg.equalsIgnoreCase("41")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789011";
			peData.interfaceID = "sensor_control";
			JSONObject json = new JSONObject();
			json.put("on", 5);
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData, new PESession(null, null, null, null, null))){
				resp = "Send control on message OK! ";
			}
			else {
				resp = "Send control on message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("42")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789011";
			peData.interfaceID = "sensor_control";
			JSONObject json = new JSONObject();
			json.put("off", 0);
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send control off message OK! ";
			}
			else {
				resp = "Send control off message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("43")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789011";
			peData.interfaceID = "sensor_control";
			JSONObject json = new JSONObject();
			json.put("getValue", 0);
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send control get message OK! ";
			}
			else {
				resp = "Send control get message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("51")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789012";
			peData.interfaceID = "sensor_control";
			JSONObject json = new JSONObject();
			json.put("on", 5);
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData, new PESession(null, null, null, null, null))){
				resp = "Send control on message OK! ";
			}
			else {
				resp = "Send control on message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("52")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789012";
			peData.interfaceID = "sensor_control";
			JSONObject json = new JSONObject();
			json.put("off", 0);
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send control off message OK! ";
			}
			else {
				resp = "Send control off message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("53")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789012";
			peData.interfaceID = "sensor_control";
			JSONObject json = new JSONObject();
			json.put("getValue", 0);
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send control get message OK! ";
			}
			else {
				resp = "Send control get message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("61")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789013";
			peData.interfaceID = "car_interface";
			JSONObject json = new JSONObject();
			json.put("forward", "0.5");
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send forward message OK! ";
			}
			else {
				resp = "Send forward message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("62")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789013";
			peData.interfaceID = "car_interface";
			JSONObject json = new JSONObject();
			json.put("backward", "0.5");
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send backward message OK! ";
			}
			else {
				resp = "Send backward message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("63")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789013";
			peData.interfaceID = "car_interface";
			JSONObject json = new JSONObject();
			json.put("left", "0.5");
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send left message OK! ";
			}
			else {
				resp = "Send left message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("64")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789013";
			peData.interfaceID = "car_interface";
			JSONObject json = new JSONObject();
			json.put("right", "0.5");
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send right message OK! ";
			}
			else {
				resp = "Send right message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("65")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789013";
			peData.interfaceID = "car_interface";
			JSONObject json = new JSONObject();
			json.put("stop", "0");
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send stop message OK! ";
			}
			else {
				resp = "Send stop message Fail! ";
			}
		}else if (msg.equalsIgnoreCase("66")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789013";
			peData.interfaceID = "cam_interface_setX";
			JSONObject json = new JSONObject();
			x = x + 10;
			if (x>180){
				x = 180;
			}
			System.out.println("X: "+x);
			json.put("control", ""+x);
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send cam_x_up message OK! ";
			}
			else {
				resp = "Send cam_x_up message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("67")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789013";
			peData.interfaceID = "cam_interface_setX";
			JSONObject json = new JSONObject();
			x = x - 10;
			if (x < 0){
				x = 0;
			}
			System.out.println("X: "+x);
			json.put("control", ""+x);
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send cam_x_down message OK! ";
			}
			else {
				resp = "Send cam_x_down message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("68")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789013";
			peData.interfaceID = "cam_interface_setY";
			JSONObject json = new JSONObject();
			y = y + 10;
			if (y > 90){
				y = 90;
			}
			System.out.println("Y: "+y);
			json.put("control",""+ y);
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send cam_y_up message OK! ";
			}
			else {
				resp = "Send cam_y_up message Fail! ";
			}
		}
		else if (msg.equalsIgnoreCase("69")){
			PEData peData = new PEData();
			peData.peID = "12345678901234567890123456789013";
			peData.interfaceID = "cam_interface_setY";
			JSONObject json = new JSONObject();
			y = y - 10;
			if (y < 0){
				y = 0;
			}
			System.out.println("Y: "+y);
			json.put("control", ""+y);
			peData.data = json.toString();
			if (MsgOperation.msgSend(peData,new PESession(null, null, null, null, null))){
				resp = "Send cam_y_down message OK! ";
			}
			else {
				resp = "Send cam_y_down message Fail! ";
			}
		}
		else if (msg.startsWith("7")&&(!msg.equalsIgnoreCase("7"))){
			String temp[] = msg.split("#");
			String fileName = "IoT_Harbor.log."+temp[1];
			resp = "Start sending log info! ";
			MonitorSocketUtil.sendInfo(ctx, resp);
			resp = fileName;
			MonitorSocketUtil.sendInfo(ctx, fileName);
			File file = new File(fileName);
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(file));
				String tempString = null;
				while ((tempString = br.readLine()) != null){
					Thread.sleep(1);
					MonitorSocketUtil.sendInfo(ctx, tempString);
				}
				br.close();
			} catch (Exception e){
				e.printStackTrace();
			}
			resp = "Stop sending log info! ";
			MonitorSocketUtil.sendInfo(ctx, resp);
			return ;
		}
		else {
			resp = "I do not know what you want! \n"
		          +"Press help for details.";
		}
		MonitorSocketUtil.sendInfo(ctx, resp);
	}
	
	private static void sendInfo(ChannelHandlerContext ctx, String msg){
		if (ctx != null){
			msg += "\n";
			ByteBuf resp = Unpooled.copiedBuffer(msg.getBytes());
			synchronized(ctx){
				ctx.writeAndFlush(resp);
			}
			System.out.println("Monitor TCP Send to ChannelHandlerContext "+ctx.name()+" , messages: "+msg);
		}
		else {
			System.out.println("Ctx null! Msg could not send to peer! ");
		}
	}
}
