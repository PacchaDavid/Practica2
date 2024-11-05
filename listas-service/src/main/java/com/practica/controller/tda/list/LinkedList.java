package com.practica.controller.tda.list;

import com.practica.controller.exception.ListEmptyException;
import java.lang.reflect.Array;

public class LinkedList<E> {
    private Node<E> header;
    private Node<E> last;
    private Integer size;

    public LinkedList() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    public Node<E> getHeader() {
        return header;
    }

    public void setHeader(Node<E> header) {
        this.header = header;
    }

    public Node<E> getLast() {
        return last;
    }

    public void setLast(Node<E> last) {
        this.last = last;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Boolean isEmpty() {
        return this.header == null || this.size == 0;
    }

    public void addHeader(E info) {
        if(isEmpty()) {
            this.header = new Node<>(info);
            this.size++;
        } else {
            if(this.last == null) {
                Node<E> newNode = new Node<>(info,this.header);
                this.header = newNode;
                this.last = this.header.getNext();
                this.size++;
            } else {
                Node<E> newNode = new Node<>(info,this.header);
                this.header = newNode;
                this.size++;
            }
        }   
    }

    public void addLast(E info) {
        if (isEmpty()) {
            addHeader(info);
        } else {
            if(this.last == null) {
                this.header.setNext(new Node<>(info));
                this.last = this.header.getNext();
                this.size++;
            } else {
                Node<E> aux = new Node<>(info);
                this.last.setNext(aux);
                this.last = aux;
                this.size++;
            }
        }
    }

    public void add(E info) {
        this.addLast(info);
    }

    public Node<E> getNode(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if(isEmpty()) {
            throw new ListEmptyException("cannot get node, list is empty");
        } else if(index < 0 || index>=this.size ) {
            throw new IndexOutOfBoundsException("cannot get node, index out of range");
        } else if(index == 0) {
            return this.header;
        } else if(index == this.size-1) {
            return this.last;
        } else {
            Node<E> aux = this.header.getNext();
            Integer count = 1;
            while(count < index) {
                aux = aux.getNext();
                count++;
            }
            return aux;
        }
    }

    public E get(Integer index) throws Exception {
        return getNode(index).getInfo();
    }

    public void deleteHeader() throws ListEmptyException {
        if(isEmpty()) {
            throw new ListEmptyException("Cannot delete header, because list is emtpy");
        } else {
            Node<E> eliminar = this.header;
            this.header = this.header.getNext();
            eliminar.setNext(null);
            eliminar = null;
            this.size--;
        }
    }

    public void deleteLast() throws ListEmptyException {
        if(isEmpty()) {
            throw new ListEmptyException("Cannot delete last, because list is empty");
        } else {
            Node<E> anterior = getNode(this.size-2);
            anterior.setNext(null);
            this.last = null;
            this.last = anterior;
            this.size--;
        }
    }


    public void delete(Integer index) throws ListEmptyException, IndexOutOfBoundsException {
        if(isEmpty()) {
            throw new ListEmptyException("Cannot delete, list is empty");
        } else if(index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Cannote delete, index is out of range");
        } else if(index == 0) {
            deleteHeader();
        } else if(index == this.size-1) {
            deleteLast();
        } else {
            Node<E> previusNode = getNode(index-1);
            Node<E> eliminar = previusNode.getNext();
            previusNode.setNext(eliminar.getNext());
            eliminar.setNext(null);
            eliminar = null;
            size--;
        }
    }

    public void update(E object, Integer index) throws Exception {
        Node<E> updateNode = getNode(index);
        updateNode.setInfo(object);
    }

    public void reset() {
        this.header = null;
        this.last = null;
        this.size = 0;
    }

    public E[] toArray() throws Exception {
        if(isEmpty()) {
            @SuppressWarnings("unchecked")
            E[] array = (E[]) Array.newInstance(getClass(), 0);
            return array;    
        }

        Class<?> clazz = this.header.getInfo().getClass();

        @SuppressWarnings("unchecked")
        E[] array = (E[])Array.newInstance(clazz,this.size);

        for(int i = 0; i < this.size; i++) {
            array[i] = get(i);
        }
        return array;
    }

    // sustituye al método toList() hecho en clase
    public void fromArrayToLinkedList(E[] array) {
        if(array == null) return;
        reset();
        for(int i = 0; i < array.length; i++) { 
            this.add(array[i]);
        }
    }

    public void recorrer() {
        if(isEmpty())
            return;

        Node<E> aux = this.header;
        while(aux!=null) {
            System.out.println(aux.getInfo()+",");
            aux = aux.getNext();
        }
    }
}
