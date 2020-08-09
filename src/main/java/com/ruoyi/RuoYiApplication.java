package com.ruoyi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.ruoyi.common.model.HandShakeOrder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.ruoyi.common.model.FrameATX;

/**
 * 启动程序
 * 
 * @author ruoyi
 */

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication {
	public static void main(String[] args) throws IOException {
		SpringApplication.run(RuoYiApplication.class, args);
	}
}
