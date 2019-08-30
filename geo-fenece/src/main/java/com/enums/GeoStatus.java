/*******************************************************************************
 * Copyright (c) 2018 Caterpillar Inc All rights reserved.
 *******************************************************************************/
package com.enums;

/**
 * The Enum GeoStatus.
 */
public enum GeoStatus {

  /** The exit. */
  EXIT(0),

  /** The entry. */
  ENTRY(1),

  /** The outer exit. */
  OUTER_EXIT(0),

  /** The outer entry. */
  OUTER_ENTRY(1),

  /** The inner entry. */
  INNER_ENTRY(2),

  /** The inner exit. */
  INNER_EXIT(3);

  /** The value. */
  private Integer value;

  /**
   * Instantiates a new geo status.
   *
   * @param value the value
   */
  GeoStatus(Integer value) {
    this.value = value;

  }

  /**
   * Gets the value.
   *
   * @return the value
   */
  public Integer getValue() {
    return value;
  }
}
