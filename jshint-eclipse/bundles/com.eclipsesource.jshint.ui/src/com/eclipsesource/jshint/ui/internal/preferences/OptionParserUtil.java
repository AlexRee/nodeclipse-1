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

import java.util.ArrayList;
import java.util.List;

import com.eclipsesource.jshint.Configuration;


public class OptionParserUtil {

  private OptionParserUtil() {
    // prevent instantiation
  }

  public static Configuration createConfiguration( String options, String globals ) {
    Configuration configuration = new Configuration();
    for( Entry entry : parseOptionString( globals ) ) {
      configuration.addPredefined( entry.name, entry.value );
    }
    for( Entry entry : parseOptionString( options ) ) {
      configuration.addOption( entry.name, entry.value );
    }
    return configuration;
  }

  static List<Entry> parseOptionString( String input ) {
    List<Entry> result = new ArrayList<Entry>();
    String[] elements = input.split( "," );
    for( String element : elements ) {
      element = element.trim();
      if( element.length() > 0 ) {
        String[] parts = element.split( ":", 2 );
        String key = parts[ 0 ].trim();
        if( key.length() > 0 ) {
          boolean value = parts.length > 1 ? Boolean.parseBoolean( parts[ 1 ].trim() ) : false;
          result.add( new Entry( key, value ) );
        }
      }
    }
    return result;
  }

  static class Entry {
    public final String name;
    public final boolean value;
    public Entry( String name, boolean value ) {
      this.name = name;
      this.value = value;
    }
  }

}
