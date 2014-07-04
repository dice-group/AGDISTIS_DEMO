package org.aksw.agdistis.model;

public class AgdistisModel {

    private String namedEntity;
    private int start;
    private String disambiguatedURL;
    private int offset;

    public String getNamedEntity() {
        return namedEntity;
    }

    public void setNamedEntity(String namedEntity) {
        this.namedEntity = namedEntity;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public String getDisambiguatedURL() {
        return disambiguatedURL;
    }

    public void setDisambiguatedURL(String disambiguatedURL) {
        this.disambiguatedURL = disambiguatedURL;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((disambiguatedURL == null) ? 0 : disambiguatedURL.hashCode());
        result = prime * result + ((namedEntity == null) ? 0 : namedEntity.hashCode());
        result = prime * result + offset;
        result = prime * result + start;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AgdistisModel other = (AgdistisModel) obj;
        if (disambiguatedURL == null) {
            if (other.disambiguatedURL != null)
                return false;
        } else if (!disambiguatedURL.equals(other.disambiguatedURL))
            return false;
        if (namedEntity == null) {
            if (other.namedEntity != null)
                return false;
        } else if (!namedEntity.equals(other.namedEntity))
            return false;
        if (offset != other.offset)
            return false;
        if (start != other.start)
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AgdistisModel [namedEntity=");
        builder.append(namedEntity);
        builder.append(", start=");
        builder.append(start);
        builder.append(", disambiguatedURL=");
        builder.append(disambiguatedURL);
        builder.append(", offset=");
        builder.append(offset);
        builder.append("]");
        return builder.toString();
    }

}
