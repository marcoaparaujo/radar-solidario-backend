package com.radar.solidario.service.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.radar.solidario.constant.ErrorCode;
import com.radar.solidario.entity.FoodStamp;
import com.radar.solidario.exception.foodStamp.notFound.FoodStampNotFoundException;
import com.radar.solidario.repository.FoodStampRepository;

import properties.foodStamp.FoodStampInstance;
import properties.foodStamp.FoodStampProperties;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Processor - FoodStamp")
public class FoodStampProcessorTest extends FoodStampProperties {

	@Mock
	private FoodStamp foodStamp;
	
	@Mock
	private FoodStamp foodStampMergeAdd;

	@Mock
	private FoodStamp foodStampMergeRemove;

	@Mock
	private Optional<FoodStamp> optFoodStamp;

	@MockBean
	private FoodStampRepository foodStampRepository;

	@Autowired
	private FoodStampProcessor foodStampProcessor;

	@BeforeEach
	public void init() {
		this.foodStamp = FoodStampInstance.instace();
		this.optFoodStamp = FoodStampInstance.instaceOptional();
		this.foodStampMergeAdd = FoodStampInstance.instaceMergeAdd();
		this.foodStampMergeRemove = FoodStampInstance.instaceMergeRemove();
	}

	@Test
	@DisplayName("Fetch a food stamp")
	public void exists() {
		when(this.foodStampRepository.findById(ID)).thenReturn(Optional.of(this.foodStamp));

		FoodStamp response = this.foodStampProcessor.exists(ID);

		assertEquals(this.foodStamp, response);
		verify(this.foodStampRepository, times(1)).findById(ID);
	}

	@Test
	@DisplayName("Fetch a non existent food stamp")
	public void existsNotFound() {
		when(this.foodStampRepository.findById(ID)).thenReturn(Optional.empty());

		FoodStampNotFoundException exception = assertThrows(FoodStampNotFoundException.class, () -> {
			this.foodStampProcessor.exists(ID);
		});

		assertEquals(ErrorCode.FOOD_STAMP_NOT_FOUND.getMessage(), exception.getMessage());
		verify(this.foodStampRepository, times(1)).findById(ID);
	}

	@Test
	@DisplayName("Fetch a food weight")
	public void existsWeight() {
		when(this.foodStampRepository.findByWeight(WEIGHT)).thenReturn(Optional.of(this.foodStamp));

		FoodStamp response = this.foodStampProcessor.exists(WEIGHT);

		assertEquals(this.foodStamp, response);
		verify(this.foodStampRepository, times(1)).findByWeight(WEIGHT);
	}

	@Test
	@DisplayName("Fetch a non existent food weight")
	public void existsWeightNotFound() {
		when(this.foodStampRepository.findByWeight(WEIGHT)).thenReturn(Optional.empty());

		FoodStampNotFoundException exception = assertThrows(FoodStampNotFoundException.class, () -> {
			this.foodStampProcessor.exists(WEIGHT);
		});

		assertEquals(ErrorCode.FOOD_STAMP_NOT_FOUND.getMessage(), exception.getMessage());
		verify(this.foodStampRepository, times(1)).findByWeight(WEIGHT);
	}

	@Test
	@DisplayName("Make a food blend add")
	public void mergeAdd() {
		when(this.foodStampRepository.findByWeight(WEIGHT)).thenReturn(this.optFoodStamp);

		FoodStamp response = this.foodStampProcessor.mergeAdd(this.foodStamp);

		assertEquals(this.foodStampMergeAdd, response);
		verify(this.foodStampRepository, times(1)).findByWeight(WEIGHT);
	}

	@Test
	@DisplayName("Do a food blend remove")
	public void mergeRemove() {
		when(this.foodStampRepository.findByWeight(WEIGHT)).thenReturn(this.optFoodStamp);

		FoodStamp response = this.foodStampProcessor.mergeRemove(this.foodStamp);

		assertEquals(this.foodStampMergeRemove, response);
		verify(this.foodStampRepository, times(1)).findByWeight(WEIGHT);
	}
}
