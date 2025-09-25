package com.example.volleyref;

public class StaffMember {
    public String role;
    public String name;
    public String license;
    @Override public String toString() {
        return (role==null?"":role)+": "+(name==null?"":name)+(license==null||license.isEmpty()?"":" (#"+license+")");
    }
}
