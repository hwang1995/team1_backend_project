package com.team1.healthcare.vo.diagnosis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MedicineVO {
  // Medicines 엔티티의 PK
  private int medicineId;

  // 약품 수량
  private int medicineDose;
}
