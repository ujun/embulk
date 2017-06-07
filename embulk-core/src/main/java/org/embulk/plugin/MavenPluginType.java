package org.embulk.plugin;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MavenPluginType
        extends PluginType
{
    private MavenPluginType(final String name, final String group, final String version)
    {
        super("maven", name);
        this.group = group;
        this.version = version;

        final StringBuilder fullNameBuilder = new StringBuilder();
        fullNameBuilder.append("maven:");
        fullNameBuilder.append(group);
        fullNameBuilder.append(":");
        fullNameBuilder.append(name);
        fullNameBuilder.append(":");
        fullNameBuilder.append(version);
        this.fullName = fullNameBuilder.toString();

        final HashMap<String, String> fullMapMutable = new HashMap<String, String>();
        fullMapMutable.put("source", "maven");
        fullMapMutable.put("name", name);
        fullMapMutable.put("group", group);
        fullMapMutable.put("version", version);
        this.fullMap = Collections.unmodifiableMap(fullMapMutable);
    }

    public static MavenPluginType create(final String name, final String group, final String version)
    {
        if (name == null) {
            throw new NullPointerException("\"name\" must not present.");
        }
        return new MavenPluginType(name, group, version);
    }

    @JsonValue
    public final Map<String, String> getJsonValue()
    {
        return this.fullMap;
    }

    @JsonProperty("group")
    public final String getGroup()
    {
        return this.group;
    }

    @JsonProperty("version")
    public final String getVersion()
    {
        return this.version;
    }

    public final String getFullName()
    {
        return this.fullName;
    }

    @Override
    public final int hashCode()
    {
        return Objects.hash(getSource(), getName(), this.group, this.version);
    }

    @Override
    public boolean equals(Object objectOther)
    {
        if (!(objectOther instanceof MavenPluginType)) {
            return false;
        }
        MavenPluginType other = (MavenPluginType) objectOther;
        return (this.getSource().equals(other.getSource()) &&
                this.getName().equals(other.getName()) &&
                this.getGroup().equals(other.getGroup()) &&
                this.getVersion().equals(other.getVersion()));
    }

    @JsonValue
    @Override
    public String toString()
    {
        return this.fullName;
    }

    private final String group;
    private final String version;
    private final String fullName;
    private final Map<String, String> fullMap;
}
