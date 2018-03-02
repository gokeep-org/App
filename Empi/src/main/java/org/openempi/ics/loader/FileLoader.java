package org.openempi.ics.loader;

import org.openempi.data.Person;

import java.io.File;

public interface FileLoader
{
	public void parseFile(File file);
	
	public void loadPerson(Person person);
}
