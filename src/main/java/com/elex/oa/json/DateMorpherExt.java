package com.elex.oa.json;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import net.sf.ezmorph.MorphException;
import net.sf.ezmorph.object.AbstractObjectMorpher;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/**
 *@author hugo.zhao
 *@since 2018/4/12 19:52
*/
public class DateMorpherExt extends AbstractObjectMorpher{
    private Date defaultValue;
    private String[] formats;
    private boolean lenient;
    private Locale locale;

    public DateMorpherExt(String[] formats) {
        this(formats, Locale.getDefault(), false);
    }

    public DateMorpherExt(String[] formats, boolean lenient) {
        this(formats, Locale.getDefault(), lenient);
    }

    public DateMorpherExt(String[] formats, Date defaultValue) {
        this(formats, defaultValue, Locale.getDefault(), false);
    }

    public DateMorpherExt(String[] formats, Date defaultValue, Locale locale, boolean lenient) {
        super(true);
        if(formats != null && formats.length != 0) {
            this.formats = formats;
            if(locale == null) {
                this.locale = Locale.getDefault();
            } else {
                this.locale = locale;
            }

            this.lenient = lenient;
            this.setDefaultValue(defaultValue);
        } else {
            throw new MorphException("invalid array of formats");
        }
    }

    public DateMorpherExt(String[] formats, Locale locale) {
        this(formats, locale, false);
    }

    public DateMorpherExt(String[] formats, Locale locale, boolean lenient) {
        if(formats != null && formats.length != 0) {
            this.formats = formats;
            if(locale == null) {
                this.locale = Locale.getDefault();
            } else {
                this.locale = locale;
            }

            this.lenient = lenient;
        } else {
            throw new MorphException("invalid array of formats");
        }
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(!(obj instanceof DateMorpherExt)) {
            return false;
        } else {
            DateMorpherExt other = (DateMorpherExt)obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(this.formats, other.formats);
            builder.append(this.locale, other.locale);
            builder.append(this.lenient, other.lenient);
            if(this.isUseDefault() && other.isUseDefault()) {
                builder.append(this.getDefaultValue(), other.getDefaultValue());
                return builder.isEquals();
            } else {
                return !this.isUseDefault() && !other.isUseDefault()?builder.isEquals():false;
            }
        }
    }

    public Date getDefaultValue() {
        return (Date)this.defaultValue.clone();
    }

    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.formats);
        builder.append(this.locale);
        builder.append(this.lenient);
        if(this.isUseDefault()) {
            builder.append(this.getDefaultValue());
        }

        return builder.toHashCode();
    }

    public Object morph(Object value) {
        if(value != null && !StringUtils.isEmpty(value.toString())) {
            if(Date.class.isAssignableFrom(value.getClass())) {
                return (Date)value;
            } else if(!this.supports(value.getClass())) {
                throw new MorphException(value.getClass() + " is not supported");
            } else {
                String strValue = (String)value;
                SimpleDateFormat dateParser = null;
                int i = 0;

                while(i < this.formats.length) {
                    if(dateParser == null) {
                        dateParser = new SimpleDateFormat(this.formats[i], this.locale);
                    } else {
                        dateParser.applyPattern(this.formats[i]);
                    }

                    dateParser.setLenient(this.lenient);

                    try {
                        return dateParser.parse(strValue.toLowerCase());
                    } catch (ParseException var6) {
                        ++i;
                    }
                }

                if(this.isUseDefault()) {
                    return this.defaultValue;
                } else {
                    throw new MorphException("Unable to parse the date " + value);
                }
            }
        } else {
            return null;
        }
    }

    public Class morphsTo() {
        return Date.class;
    }

    public void setDefaultValue(Date defaultValue) {
        this.defaultValue = (Date)defaultValue.clone();
    }

    public boolean supports(Class clazz) {
        return String.class.isAssignableFrom(clazz);
    }


}
