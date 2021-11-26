package com.example.Lab06;

import controller.Calculator;
import controller.Installment;
import controller.InstallmentType;
import controller.Timetable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Lab06ApplicationTests {
	Calculator calculator = new Calculator();

	@Test
	public void timetableShouldHave10Installments(){
		Timetable timetable = new Timetable(10000,10, InstallmentType.constant,0.01,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(10,installments.size());
	}

	@Test
	public void timetableShouldHaveAllValuesEqualTo1(){
		Timetable timetable = new Timetable(1,1,InstallmentType.decreasing,1,1);
		Assertions.assertEquals(1,timetable.getAmount());
		Assertions.assertEquals(1,timetable.getInstallmentCount());
		Assertions.assertEquals(1,timetable.getFixedFee());
		Assertions.assertEquals(1,timetable.getPercentage());
	}

	@Test
	public void timetableShouldHaveInstallmentTypeSetAsConstant(){
		Timetable timetable = new Timetable(1,1,InstallmentType.constant,1,1);
		Assertions.assertEquals(InstallmentType.constant,timetable.getInstallmentType());
	}

	@Test
	public void timetableShouldHaveConstantInstallments(){
		Timetable timetable = new Timetable(1000,4,InstallmentType.constant,0,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(InstallmentType.constant,timetable.getInstallmentType());
		Assertions.assertEquals(250,installments.get(0).getAmount());
		Assertions.assertEquals(250,installments.get(1).getAmount());
		Assertions.assertEquals(250,installments.get(2).getAmount());
		Assertions.assertEquals(250,installments.get(3).getAmount());
	}

	@Test
	public void timetableShouldHaveDecreasingInstallments(){
		Timetable timetable = new Timetable(1000,4,InstallmentType.decreasing,0.01,0);
		List<Installment> installments =  calculator.calculateInstalments(timetable);
		Assertions.assertEquals(InstallmentType.decreasing,timetable.getInstallmentType());
		Assertions.assertTrue(installments.get(0).getAmount() > installments.get(1).getAmount());
		Assertions.assertTrue(installments.get(1).getAmount() > installments.get(2).getAmount());
		Assertions.assertTrue(installments.get(2).getAmount() > installments.get(3).getAmount());
	}

}
