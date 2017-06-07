package org.embulk.plugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import org.junit.Test;

public class TestPluginType
{
    @Test
    public void testEquals()
    {
        PluginType type = PluginType.createFromStringForTesting("a");
        assertTrue(type instanceof DefaultPluginType);
        assertTrue(type.equals(type));

        assertTrue(type.equals(PluginType.createFromStringForTesting("a")));
        assertFalse(type.equals(PluginType.createFromStringForTesting("b")));
    }

    @Test
    public void testMapping1()
    {
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("source", "default");
        mapping.put("name", "c");

        PluginType type = PluginType.createFromStringMapForTesting(mapping);
        assertTrue(type instanceof DefaultPluginType);
        assertTrue(type.equals(type));

        assertTrue(type.equals(PluginType.createFromStringForTesting("c")));
        assertFalse(type.equals(PluginType.createFromStringForTesting("d")));
    }

    @Test
    public void testMapping2()
    {
        HashMap<String, String> mapping = new HashMap<String, String>();
        mapping.put("source", "maven");
        mapping.put("name", "e");
        mapping.put("group", "org.embulk.foobar");
        mapping.put("version", "0.1.2");

        PluginType type = PluginType.createFromStringMapForTesting(mapping);
        assertTrue(type instanceof MavenPluginType);
        MavenPluginType mavenType = (MavenPluginType) type;
        assertTrue(mavenType.equals(mavenType));
        assertEquals(mavenType.getName(), "e");
        assertEquals(mavenType.getGroup(), "org.embulk.foobar");
        assertEquals(mavenType.getVersion(), "0.1.2");
        assertEquals(mavenType.getFullName(), "maven:org.embulk.foobar:e:0.1.2");
    }
}
