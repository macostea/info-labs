using System;

namespace Lab2
{
	namespace Lab2_Utils {
		namespace Lab2_LinkedList {
			public class Node<T>
			{
				public T data;
				public Node<T> next;

				public Node(T data){
					this.data = data;
				}
			}
		}
	}
}

