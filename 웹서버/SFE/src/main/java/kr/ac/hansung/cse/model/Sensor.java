package kr.ac.hansung.cse.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Sensor {
	private int id;
	private String sensorValue;
	private int sensorX;
	private int sensorY;
}
