/*******************************************************************************
 * Copyright (c) 2012 EclipseSource.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ralf Sternberg - initial implementation and API
 ******************************************************************************/
package com.eclipsesource.jshint.ui.internal.preferences;

import java.util.List;

import org.junit.Test;

import com.eclipsesource.jshint.Configuration;
import com.eclipsesource.jshint.ui.internal.preferences.OptionParserUtil.Entry;

import static org.junit.Assert.*;


public class OptionParserUtil_Test {

  @Test
  public void createConfiguration() {
    Configuration result = OptionParserUtil.createConfiguration( "opt1: true", "predef1: false" );

    assertEquals( "{\"predef\": {\"predef1\": false}, \"opt1\": true}", result.toJson() );
  }

  @Test
  public void createConfiguration_withEmptyParameters() {
    Configuration result = OptionParserUtil.createConfiguration( "", "" );

    assertEquals( "{}", result.toJson() );
  }

  @Test( expected = NullPointerException.class )
  public void createConfiguration_withOptionsNull() {
    OptionParserUtil.createConfiguration( null, "" );
  }

  @Test( expected = NullPointerException.class )
  public void createConfiguration_withGlobalsNull() {
    OptionParserUtil.createConfiguration( "", null );
  }

  @Test( expected = NullPointerException.class )
  public void parseNull() {
    OptionParserUtil.parseOptionString( null );
  }

  @Test
  public void parseEmpty() {
    List<Entry> result = OptionParserUtil.parseOptionString( "" );

    assertTrue( result.isEmpty() );
  }

  @Test
  public void parseSingle() {
    List<Entry> result = OptionParserUtil.parseOptionString( "foo: true" );

    assertEquals( 1, result.size() );
    assertEquals( "foo", result.get( 0 ).name );
    assertTrue( result.get( 0 ).value );
  }

  @Test
  public void parseTwoOptions() {
    List<Entry> result = OptionParserUtil.parseOptionString( "foo: true, bar: false" );

    assertEquals( 2, result.size() );
    assertEquals( "foo", result.get( 0 ).name );
    assertTrue( result.get( 0 ).value );
    assertEquals( "bar", result.get( 1 ).name );
    assertFalse( result.get( 1 ).value );
  }

  @Test
  public void parseAdditionalWhitespace() {
    List<Entry> result = OptionParserUtil.parseOptionString( "\tfoo : true  ,\n\t bar  : false  " );

    assertEquals( 2, result.size() );
    assertEquals( "foo", result.get( 0 ).name );
    assertTrue( result.get( 0 ).value );
    assertEquals( "bar", result.get( 1 ).name );
    assertFalse( result.get( 1 ).value );
  }

  // TODO check for illegal names
  @Test
  public void parseWhitespaceInName() {
    List<Entry> result = OptionParserUtil.parseOptionString( "foo bar: true" );

    assertEquals( 1, result.size() );
    assertEquals( "foo bar", result.get( 0 ).name );
    assertTrue( result.get( 0 ).value );
  }

  @Test
  public void parseNonBooleanValue() {
    List<Entry> result = OptionParserUtil.parseOptionString( "foo: bar" );

    assertEquals( 1, result.size() );
    assertEquals( "foo", result.get( 0 ).name );
    assertFalse( result.get( 0 ).value );
  }

}
