package org.aksw.agdistis.model;

public class Entity implements Comparable<Entity> {

    private String entity;
    private int begin;
    private int end;

    public Entity(String entity, int begin, int end) {
        super();
        this.entity = entity;
        this.begin = begin;
        this.end = end;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Entity [entity=");
        builder.append(entity);
        builder.append(", begin=");
        builder.append(begin);
        builder.append(", end=");
        builder.append(end);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + begin;
        result = prime * result + end;
        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
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
        Entity other = (Entity) obj;
        if (begin != other.begin)
            return false;
        if (end != other.end)
            return false;
        if (entity == null) {
            if (other.entity != null)
                return false;
        } else if (!entity.equals(other.entity))
            return false;
        return true;
    }

    @Override
    public int compareTo(Entity o) {
        if (this.begin < o.begin) {
            return -1;
        } else if (this.begin > o.begin) {
            return 1;
        }
        return 0;
    }

}
