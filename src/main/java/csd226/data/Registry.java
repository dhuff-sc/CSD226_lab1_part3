package csd226.data;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "registry")
public class Registry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255)
    private String registryKey;

    @Column(columnDefinition = "text")
    private String registryValue;

    public Registry() {
    }

    public Registry(String registryKey, String registryValue) {
        this.registryKey = registryKey;
        this.registryValue = registryValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegistryKey() {
        return registryKey;
    }

    public void setRegistryKey(String registryKey) {
        this.registryKey = registryKey;
    }

    public String getRegistryValue() {
        return registryValue;
    }

    public void setRegistryValue(String registryValue) {
        this.registryValue = registryValue;
    }

    public String toString() {
        return "Registry{" +
                "id=" + id +
                ", registryKey='" + registryKey + '\'' +
                ", registryValue='" + registryValue + '\'' +
                '}';
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Registry registry = (Registry) obj;
        return id.equals(registry.id) && registryKey.equals(registry.registryKey) && registryValue.equals(registry.registryValue);
    }

    public int hashCode() {
        return Objects.hash(id, registryKey, registryValue);
    }

}
