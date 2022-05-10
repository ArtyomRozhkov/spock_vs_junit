package com.rozhkov.spockvsjunit

import spock.lang.Specification

class SpockSetupAndTurnDownTest extends Specification {

  def setupSpec() {
    println("setup test")
  }

  def cleanupSpec() {
    println("cleanup test")
  }

  def setup() {
    println("before each")
  }

  def cleanup() {
    println("cleanup after each")
  }

  def test() {
    println("test")
    expect:
    Math.abs(-1) == 1
  }
}
