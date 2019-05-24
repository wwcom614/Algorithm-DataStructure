重温常用的一些算法与数据结构，编码实践并逻辑梳理归纳备忘。   
1.线性数据结构：  
动态数组、链表、普通队列、栈、哈希表。    
2.树形数据结构：  
(1)二叉树：二分搜索树、AVL(自平衡)、红黑树(统计性能高，自平衡，最常用)。  
(2)堆(优先队列)、线段树。  
(3)多叉树：Trie、并查集。  
3.抽象数据结构：  
有序集合TreeSet，有序映射TreeMap，底层基于平衡树实现。  
无序集合HashSet，无序映射HashMap，底层基于哈希表实现。  

## 数组Array 
- Array.java：  
基于Java中最基础的数组，自定义二次封装实现了一个动态数组：      
1.自定义一个数组类Array，使用泛型，让数组类支持存储任意元素。   
注意：Java不支持new泛型，只能是先new个Object出来，然后强制类型转换为泛型类型。  
2.自定义有参构造方法：传入数组容量capacity构造Array；便捷扩展自定义无参构造方法：调用方如果没传入数组容量capacity，将使用该方法预制初始容量。    
3.自定义获取数组中当前元素数量的方法getSize；自定义获取数组容量的方法getCapacity；自定义查询数组是否为空的方法isEmpty。     
4.自定义一个内部方法resize(均摊时间复杂度O(1))，用于数组动态扩容和缩容：  
(1)实例化一个newCapacity的扩容后数组，并拷贝扩容前数组的数据元素到扩容后数组。  
(2)扩容完毕后，data引用直接指向扩容后的newData对象。    
5.自定义在数组索引index位置插入一个元素的方法add(时间复杂度O(n))：  
(1)如果数组满了调用resize动态扩容，一次性动态扩容2倍。        
(2)正常插入过程是：从最后一个元素开始，每个依次向后挪一个，一直到把index的数据也向后挪走；然后再安心的在index插入新元素；数组当前元素数量+1。     
基于上述方法，自定义2个便捷方法：  
(1)在数组所有已有元素最后面插入一个元素addLast(时间复杂度O(1))；  
(2)在数组所有已有元素最前面插入一个元素addFirst(时间复杂度O(n))。   
6.自定义修改index位置的元素的方法set(时间复杂度O(1))。  
7.自定义获取index位置的元素的方法get(时间复杂度O(1))。     
基于上述方法，自定义2个便捷方法：
(1)获取数组最后一个元素getLast(时间复杂度O(1))。  
(2)获取数组首个元素getFirst(时间复杂度O(1))。  
8.自定义查找数组中是否有元素e的方法contains(时间复杂度O(n))。  
注意：需使用值比较equals，不能使用引用比较==。   
9.自定义查找数组中首个元素e所在的索引值，如果不存在，返回-1的方法findFirstElement(时间复杂度O(n))。  
10.自定义在数组索引index位置删除一个元素，并返回该删除的元素remove(时间复杂度O(n))：  
(1)从删除元素index开始，每个依次向前挪一个，一直到size-1末尾元素。  
(2)数组当前元素数量-1。  
(3)清空最后一个不用的元素引用，让JVM垃圾回收。  
(4)为避免与动态扩容形成计算复杂度震荡，采用更懒一点的方式解决：如果已使用容量低于系统容量1/4时，动态缩容一半，降低对象存储占用。  
基于上述方法，自定义2个便捷方法：  
(1)在数组所有已有元素最后面删除一个元素removeLast(时间复杂度O(1))；  
(2)在数组所有已有元素最前面删除一个元素removeFirst(时间复杂度O(n))。   
11.从数组中删除首个值为e的元素removeFirstElement(时间复杂度O(n))，删除成功返回true，未找到返回false。  
12.重写toString方法，打印结果便于测试验证。  

- Student.java： 
自定义一个对象类型，用于验证自定义数组类使用泛型后对自定义对象作为元素存储和操作的正确性。  

- ArrayTest.java： 
@BeforeClass构造了一份数组数据，使用@FixMethodOrder(MethodSorters.NAME_ASCENDING)确保测试方法按方法名依次执行。  
编写上述自定义Array类每个方法的测试类，经测试OK。  

## 栈Stack 
- Stack.java：  
栈有多种实现方式，自定义一个接口，定义栈提供的通用方法：push、pop、peek等。   

- ArrayStack.java，ArrayStackTest.java:   
基于上述自己编写的动态数组类Array的能力，实现栈Stack接口功能:   
1.自定义入栈方法push(均摊时间复杂度O(1))，调用数组类Array的addLast能力。      
2.自定义出栈方法pop(均摊时间复杂度O(1))，调用数组类Array的removeLast能力。  
3.自定义查询栈顶元素方法peek(时间复杂度O(1))，调用数组类Array的getLast能力。  

- BracketsMatch.java，BracketsMatchTest.java    
应用栈解决一个问题--leetcode网站上的栈问题中，有个括号匹配的问题，序号20。  
自己扩展一下，输入一个表达式，判断括号匹配是否合法。   
1.依次遍历字符串String的每个字符，提取括号，按顺序存储到StringBuilder中。  
2.此时，StringBuilder sb中只有括号，且出现顺序与输入String中一致。  
3.挨个看看这些括号，如果字符是左括号，入栈stack。注意，该栈只入栈这3种左括号字符。   
4.从头遍历该只有括号的字符串sb：  
(1)如果开头不是左括号的，不合法。  
(2)如果栈内的左括号 和 下一个字符中的括号不匹配，不合法。  
(3)匹配完成后，栈内不能还有左括号，那说明没找到与此左括号的右括号，不合法。  
5.BracketsMatchTest：编写一个测试类，输入几种表达式，看看BracketsMatch是否符合预期。  

- LinkedListStack.java，LinkedListStackTest.java   
1.基于链表重写Stack的push方法：linkedList.addFirst(e)链表增加头部元素，时间复杂度为O(1)。  
2.基于链表重写Stack的pop方法：linkedList.removeFirst()链表删除头部元素，时间复杂度为O(1)。  
3.基于链表重写Stack的peek方法：linkedList.getFirst()链表获取头部元素，时间复杂度为O(1)。  

- StackPerfTest.java   
编写了一个性能测试类，对数组栈ArrayStack(时间复杂度O(1))、
链表栈LinkedListStack(时间复杂度O(1))做大批量数据的入栈和出栈的性能测试。   
数据量大时：  
同为O(1)时间复杂度，执行时间无明显差距。  

## 队列Queue 
- Queue.java：  
队列有多种实现方式，自定义一个接口，定义队列提供的通用方法enqueue、dequeue、getFront等。  

- ArrayQueue.java，ArrayQueueTest.java:   
基于自己编写的动态数组类Array的能力，实现队列Queue接口功能:   
1.自定义队尾入队列方法enqueue(均摊时间复杂度O(1))，调用数组类Array的addLast能力。      
2.自定义队首出队列方法dequeue(时间复杂度O(n))，调用数组类Array的removeFirst能力。  
3.自定义查询队首元素方法getFront(时间复杂度O(1))，调用数组类Array的getFirst能力。

- LoopQueue.java，LoopQueueTest.java   
基于自定义的动态数组实现的队列ArrayQueue，出队dequeue时，由于要将所有元素都向队首移动一次，导致其均摊复杂度为O(n)。  
为解决这个问题，使用循环数组实现队列，关键点：使用front索引标识首个元素，使用tail索引标识末尾元素，
队首元素出队dequeue时，将front向后指向，而不用移动元素。  
1.自定义isEmpty方法：循环队列的tail==front重合时表示队列为空。   
2.自定义getCapacity方法：调用者设定的容量capacity，比实际占用的容量少1(data.length-1)。需要预留(浪费)一个元素空间标识循环队列满。  
3.自定义resize方法(均摊时间复杂度O(1))：   
(1)操作仅仅是扩容或缩容，不涉及出队和入队。  
(2)原循环队列数组，从front开始，逐个复制到新数组中。注意移动队尾tail循环，要取模。  
4.自定义enqueue方法(均摊时间复杂度O(1))：   
(1)(tail+1)%capacity==front时表示队列满要扩容，调用resize方法扩容。  
(2)正常队尾入队操作，注意队尾循环，要取模：tail= (tail+1)%data.length。   
5.自定义dequeue方法(均摊时间复杂度O(1))：   
(1)判断队列是否为空保护。  
(2)注意出队的队首元素JVM内存回收，以及移动队首front循环，要取模。  
(3)判断是否需要缩容resize，考虑避免动态缩容与动态扩容形成计算复杂度震荡。  
6.自定义getFront方法(时间复杂度O(1))：获取队首元素值。  
7.编码过程中，特意练习了2种遍历循环队列的方法，扩容方法中1种，toString方法中1种。   

- LinkedListQueue.java，LinkedListQueueTest.java   
链表的head端插入、head端删除元素都容易。  
基于链表实现队列，需要增加尾指针tail。  
tail端插入元素容易，但tail端删除元素不容易。  
所以：
从tail端插入元素--队尾enqueue，从head端删除元素--队首dequeue。  
因为不涉及在中间插入node，所以不引入dummyHead。需注意链表为空的情况处理。  
1.基于链表重写Queue的enqueue方法：时间复杂度为O(1)。  
核心：  
(1)注意队列为空的处理。  
(2)tail.next = new Node(e)--入队增加：当前tailnext赋值为新加入的节点。  
(3)tail = tail.next--tail向后移动一个节点。    
2.基于链表重写Queue的dequeue方法：时间复杂度为O(1)。  
核心：  
(1)注意队列为空的处理。  
(2)head = head.next--出队删除：head节点向后移动一个节点。  
(3)delNode.next = null--便于JVM回收删除的节点。     
(4)如果链表只有1个元素，出队后链表为空，此时head和tail都要为null才行。  
3.基于链表重写Queue的getFront方法：head.e--时间复杂度为O(1)。  

- QueuePerfTest.java   
编写了一个性能测试类，对数组队列ArrayQueue(时间复杂度O(n))、循环数组队列LoopQueue(时间复杂度O(1))、
链表队列LinkedListQueue(时间复杂度O(1))做大批量数据的入队和出队的性能测试。  
数据量大时：  
O(n)和O(1)时间复杂度，执行时间会有明显差距；同为O(1)时间复杂度，执行时间无明显差距。  

## 链表Linked List   
链表是一种最简单的真正动态的数据结构，不用考虑扩容问题。  
链表的头部head插入和删除操作复杂度为O(1)。  
增加尾指针tail的链表，可使尾部插入操作均摊复杂度降为O(1)，但删除操作并不容易。 
链表的缺点：随机访问某节点的时间复杂度比数组的高、性能差。  

- LinkedList.java，LinkedListTest.java：  
1.链表是由节点构成的，所以首先构造节点Node内部类。private对外屏蔽链表内部的节点信息和实现细节。  
2.虚拟头结点dummyHead：因为在链表头插入元素 与 在链表其他位置插入元素逻辑上有区别。  
在链表头部head之前设置一个虚拟头结点dummyHead来解决该问题。  
注意：此时即使链表为空，也存在一个节点--虚拟头结点dummyHead。    
3.思维练习：add(int index, E e)--在链表index位置增加一个元素e(时间复杂度O(n))。核心prev.next = new Node(e, prev.next)。   
4.基于上述add方法，addFirst在链表头head添加新的元素e(时间复杂度O(1))，addLast在链表末尾增加一个元素e(时间复杂度O(n))。  
5.思维练习：remove(int index, E e)--在链表index位置删除一个元素e(时间复杂度O(n))。核心prev.next = delNode.next;  delNode.next = null。   
6.基于上述remove方法，removeFirst在链表头head删除新的元素e(时间复杂度O(1))，removeLast在链表末尾删除一个元素e(时间复杂度O(n))。  
7.removeFirstElement(E e)：从链表中删除首个值为e的节点(时间复杂度O(n))。  
8.思维练习：get方法，循环移动指针获取链表index位置的元素e(时间复杂度O(n))。  
9.基于上述get方法，getFirst获得链表首个元素(时间复杂度O(1))，getLast获得链表末尾元素(时间复杂度O(n))。  
10.思维练习：set方法，循环移动指针修改链表index位置的元素为e(时间复杂度O(n))。   
11.contains方法，遍历链表中查找是否有元素e(时间复杂度O(n))。   

- LinkedListNode.java，RemoveAllFindElement.java，RemoveAllFindElementTest.java：  
到LeetCode上找了个链表相关题目203：删除一个链表内所有指定元素。练习一下：  
1.LinkedListNode：  
  (1)上述链表的节点类定义。  
  (2)为测试方便，编写了一个构造方法：输入数组，生成链表返回。  
  (3)为测试方便，重写toString方法，打印链表。  
2.RemoveAllFindElement：  
(1)removeAllFindElementByLoop(循环迭代方式遍历链表)：  
链表增加或删除操作为统一逻辑，还是定义一个dummyHead方便。从dummyHead开始遍历链表一直到末尾：    
如果prev.next的节点中存储的就是要删除的值findValue，于是prev.next直接跳过该节点 prev.next = prev.next.next;  
如果prev.next的节点中存储的值不是要删除的值findValue，于是移动到下一个节点重复上述过程prev = prev.next。  
最终返回删除所有指定元素的链表的头节点return dummyHead.next。  
(2)removeAllFindElementByRecursion(递归方式遍历链表。depth是为了方便打印递归过程加入的参数，真正使用时可去除)：  
先求解最基本的问题，确保递归终止条件：if (head == null)   。    
然后把原问题转化为更小的问题，还是方法调用，不是A调用B，是A调用A自己而已：  
head.next = removeAllFindElementByRecursion(head.next, findValue);  
return head.val==findValue ? head.next : head 。  
3.RemoveAllFindElementTest：对上述循环迭代方法和递归方法分别进行了测试并通过。递归方式因为不好理解，加入了递归深度等信息打印便于理解。  

## 二分搜索树BST(Binary Search Tree)    
树和链表一样，也是一种动态数据结构。   
二叉数是一种特殊的树，在每个父节点最多有2个子节点。  
二分搜索树是一种特殊的二叉树，其左节点比父节点小，右节点比父节点大。  
缺点：顺序增加元素，会让二分搜索树退化成链表，时间复杂度提升，性能降到最低。解决方案是使用自平衡的二分搜索树，例如AVL树。    
非线性数据结构，递归方法实现功能更简便。  
  
- BSTTree.java，BSTTreeTest.java   
1.向二分搜索树中增加新元素e的add方法(平均时间复杂度O(logn))：   
调用内部私有方法root = add(root, e)，对外屏蔽内部节点细节，递归方法向node为根节点的二分搜索树中插入元素e，返回插入新节点后的二分搜索树的根节点。    
要增加的元素 < 当前节点node的元素，向左节点递归继续寻找：if (e.compareTo(node.e) < 0)  node.left = add(node.left, e);    
要增加的元素 > 当前节点node的元素，向右节点递归继续寻找：else if (e.compareTo(node.e) > 0) node.right = add(node.right, e);    
未考虑重复元素，如果要考虑重复元素，下述使用<=归到左子树 或 使用>=归到右子树。  
2.二分搜索树中是否包含元素e的contains方法(平均时间复杂度O(logn))：    
调用内部私有的内部方法contains(root, e)，对外屏蔽内部节点细节，递归方法查询node为根节点的二分搜索树中是否含有元素e。   
if (e.equals(node.e)) return true;   
else if (e.compareTo(node.e) < 0) return contains(node.left, e);  
else {//e.compareTo(node.e) > 0 return contains(node.right, e);}   
3.深度优先遍历树的3种方法：前序遍历、中序遍历、后序遍历(平均时间复杂度O(logn))。  
3.1前序遍历：父节点->左叶子节点->右叶子节点，应用：二叉树状toString打印。       
3.1.1preOrderTraverseByStack：先尝试下非递归方法，基于自定义stack模拟系统栈实现前序遍历。  
核心：先出栈当前节点。注意压栈顺序。因为我们想先看左子树，所以要先将非空右子节点压入栈底，非空左子节点后压入在栈顶。    
3.1.2preOrderTraverse：基于递归实现，逻辑简单很多。     
3.2midOrderTraverse中序递归遍历：左叶子节点->父节点->右叶子节点，应用：二分搜索树元素从小到大顺序。    
3.3postOrderTraverse后序递归遍历：左叶子节点->右叶子节点->父节点，最后才是根节点，应用：其他语言中可用于内存释放。    
4.广度优先遍历：层序遍历--基于队列实现(平均时间复杂度O(logn))。  
levelOrderTraverse：当前父节点先出队，使用队列先进先出，想先看左子节点，所以左子节点先入队。  
5.getMin(平均时间复杂度O(logn))：查找二分搜索树的最小元素--最左有值的分支元素。  
6.getMax(平均时间复杂度O(logn))：查找二分搜索树的最大元素--最右有值的分支元素。  
7.removeMin(平均时间复杂度O(logn))：二分搜索树删除最小值节点，并返回最小值。  
递归向最左侧子树移动，直至到达最小值节点。  
注意删除左子树时，如果有右子树，需要存下来，然后去除node的右子树指针。    
8.removeMax(平均时间复杂度O(logn)):二分搜索树删除最大值节点，并返回最大值。  
递归向最右侧子树移动，直至到达最大值节点。  
注意删除右子树时，如果有左子树，需要存下来，然后去除node的左子树指针。    
9.以上5~8功能为基础，实现从二分搜索树中，删除元素值为e的节点remove方法(平均时间复杂度O(logn))：  
(1)递归终止条件。找到最后也没找到值为e的节点，不用删除。  
(2)if(e.compareTo(node.e) < 0 )，向左子树递归：node.left = remove(node.left, e);  
(3)if(e.compareTo(node.e) > 0 )，向由子树递归：node.right = remove(node.right, e);  
(4)e.equals(node.e),这就是要删除的node节点了。  
(5)如果要删除的node节点没有左子树，把右子树保存下来返回即可，删除node的右子树。  
(6)如果要删除的node节点没有右子树，把左子树保存下来返回即可，删除node的左子树。 
(7)如果要删除的node节点既有左子树，又有右子树，找到比待删除node节点大的最小的节点：
也就是node节点右子树中的最小节点，作为后继successor节点。  
将后继successor替代node节点，替代后：  
7.1)后继successor的右子树为原node节点的右子树去除最小节点。  
7.2)后继successor的左子树为原node节点的左子树。   
7.3)标记JVM释放删除的node节点，返回后继节点successor。  

## 集合Set   
集合set不存放重复元素。分为有序集合和无序集合。 

- BSTSet.java   
二分搜索树是实现有序集合的一种底层数据结构，时间复杂度为O(h)，h是二分搜索树的高度。   
满二叉树时，二分搜索树增删查的平均时间复杂度为O(logn)，退化为链表时最差时间复杂度为O(n)。  
注：BST本身已排重，所以set.add方法无需特殊处理。     

- LinkedListSet.java   
链表是实现无序集合的一种的底层数据结构，无序集合推荐使用hash表作为底层数据结构，性能更高。  
查：基于链表的contains(e)方法，需要遍历链表，所以时间复杂度为O(n)。   
增：基于链表的addFirst方法，时间复杂度O(1)。但排重需要先调用链表的contains方法，时间复杂度O(n)。所以时间复杂度为O(n)。   
删：基于链表的removeFirstElement(e)方法，需要遍历链表，所以时间复杂度为O(n)。  

- UniqueMorseRepresentations.java，UniqueMorseRepresentationsTest.java         
链表相关练习：到leetcode找了道题目，序号804，英文字母组合摩斯码排重。    
1.将leetcode上摩斯码与26个小写英文字母的映射定义，存放到一个字符串数组morseCodes中。   
2.遍历输入的单词数组，依次取出每个单词。   
3.参照上述字符串数组morseCodes，将单词逐个字母转换为摩斯码，add到set中排重。  
4.返回set.size就是要的不重复摩斯码数量。   
5.编写测试类，结果为2，与leetcode答案一致。    

## 映射/字典Map   
- LinkedListMap.java    
基于链表底层数据结构，实现了一个无序Map(key无顺序)。太慢，用Hash表实现性能高。  
相对于之前实现的链表，其数据结构中存储的元素变为KV2个元素值。  
同样引入dummyHead简化首个元素和后续元素的统一逻辑处理。  
1.其他方法公有操作自定义封装一个getNode内部方法：逐个判断key是否存在，如果存在返回对应节点，不存在返回null。  
2.contains方法(时间复杂度O(n))：看getNode(key)是否为null即可。  
3.get方法(时间复杂度O(n))：如果getNode(key)返回了Node，取出该node中的value；否则返回null。  
4.add方法(时间复杂度O(n))：如果getNode(key)没找到，那把这个节点加在链表头部；如果找到了，更新其value。  
5.set方法(时间复杂度O(n))：如果getNode(key)没找到，抛异常(与add方法区分开来)。如果找到了，更新其value。  
6.remove方法(时间复杂度O(n))：略微复杂些，前置节点遍历链表先找，找到了退出遍历，记录被删除的节点最后返回其值，链表跳过被删除的的节点，JVM删除被删除的节点引用。   

- LinkedListMapTest.java   
基于上述自己用链表实现的Map--LinkedListMap，实现一个词频统计功能测试验证。

- BSTMap.java  
基于二分搜索树底层数据结构，实现了一个有序Map(key有顺序)。  
相对于之前实现的BST，其数据结构中存储的元素变为KV2个元素值。  
其他和BST区别不大，平均时间复杂度O(logn)。  

- BSTMapTest.java   
基于上述自己用二分搜索树实现的Map--BSTMap，实现一个词频统计功能测试验证。  

## 堆Heap和优先队列PriorityQueue    
完全二叉树是一种二叉树，一层一层放元素，放完为止。   
完全二叉树不是满二叉树，完全二叉树右下角有元素空缺，完全二叉树叶子节点深度相差<=1。       
二叉堆是一种完全二叉树，底层可以用数组实现，左右子节点和父节点之间关系可以用数组索引方便表示。  
最大堆是一种二叉堆：父节点比左右子节点都大，但相同层级的值不一定大于下一层级的值。  
最大堆和最小堆是构建优先队列的数据结构，使其出队和入队时间复杂度都能达到O(logn)级别。  
优先队列主要用于解决在N个元素中选出前M个元素的问题。        

- MaxHeap.java     
1.基于动态数组构建最大堆：动态数组的首个元素作为最大堆的root结点，依次按相同层级顺序排放最大堆的各个节点。    
对于动态数组中索引是index的最大堆中某节点：     
(1)getParent其父节点索引是(index-1)/2。  
(2)getLeftChild左子节点索引是index\*2+1。  
(3)getRightChild右子节点索引是index\*2+2。  
2.add方法，最大堆插入元素，时间复杂度O(logn)：  
(1)增加元素到最大堆的最底层末尾，也就是动态数组的末尾。  
(2)siftUp方法(时间复杂度O(logn))：内部方法，将新插入底层的元素逐步上浮到合适节点，以满足最大堆性质(父节点值比左右子节点都大)。  
3.removeMax方法，取出最大堆的最大元素，并删除该节点--顶层root节点(数组索引为0位置)，时间复杂度O(logn)。  
(1)先查询最大堆的最大值，并记录下来，最后作为结果返回。  
(2)删除最大堆的最大元素根节点，并移动节点恢复最大堆性质：  
(2-1)将最大堆的root节点和末尾节点交换位置。  
(2-2)交换后，删除末尾节点。  
(2-3)siftDown方法(时间复杂度O(logn))：顶层root节点(数组索引为0位置)下沉，直到满足最大堆性质：  
(2-3-1)当curIndex节点存在左子节点(左子节点的索引在动态数组范围内)；  
(2-3-2)先把左子节点的索引存下来，默认curIndex节点的最大值子树maxChildIndex是左子树的值；  
(2-3-3)除非curIndex节点右子节点也存在(右子节点的索引也在动态数组范围内)，并且右子树值>左子树值，此时curIndex节点的最大值子树maxChildIndex是右子树的值；     
(2-3-4)确定好了curIndex节点的最大值子树maxChildIndex，与curIndex节点值做比较。  
(2-3-5)如果curIndex节点的最大值子树maxChildIndex 比 curIndex节点值小，那么已经符合最大堆性质，可以结束移动curIndex节点。  
(2-3-6)否则说明curIndex节点还没下沉到位，curIndex节点与maxChildIndex节点交换位置后继续下沉。  
4.replace方法，取出(获取并删除)最大堆中最大元素，插入元素e：新插入元素直接插入到堆顶，然后调用siftDown方法将该新插入节点逐步下沉交换，直到恢复最大堆性质。  
5.Heapify构造方法：将任意数组整理成最大堆，时间复杂度O(n)，而如果从无到有逐个add元素构造最大堆的时间复杂度O(nlogn)。  
(1)首先将输入数组转化为动态数组。  
(2)找到最后一个非叶子节点--方法是找到最后一个节点的父节点。  
(3)然后依次向前，直到到达root节点，逐个节点siftDown，直到满足最大堆性质。  

- PriorityQueue.java  
基于最大堆数据结构，原样封装一下就是优先级队列，使其出队和入队时间复杂度都能达到O(logn)级别。  

- MaxHeapTest.java
1.testMaxHeapFunction，测试验证自己写的最大堆的add、removeMax方法功能正确性。  
2.testMaxHeapPerf，编码构造大数据量对比分析两者性能时间差异：
(1)直接从无到有逐个添加元素构造最大堆，时间复杂度O(nlogn)。   
(2)heapify方式基于数组构造最大堆，时间复杂度O(n)。   
 
- TopKFrequency.java，TopKFrequencyTest.java      
Leetcode上的一道题目，序号347：给定一个非空的整数数组，返回其中出现频率前k高的元素。  
1.将数组转化为元素为key，出现次数为value的Map。  
2.基于自己写的最大堆实现的优先队列，遍历上述构造好的元素为key，出现次数为value的Map的元素们keys。  
(1)优先队列中如果少于k个元素，元素直接入队；   
(2)优先队列中已有k个元素，又来一个新的元素，那么用优先队列头部(最大堆root节点元素)--优先队列中目前优先级最低的元素，
与新来的元素对比；  
(3)如果新来的元素优先级更高(出现次数freq值更大)，
优先队列头部元素出队--removeMax方法删除最大堆root节点元素，
新来的元素入队--add(e)方法最大堆插入元素。  
3.遍历完成后，优先队列中就是所需的TopK元素，存储到链表中返回。  
注：  
1.优先队列中的元素需要有可比性，所以将做好元素频率统计的Map转化为Freq对象，并重写其compareTo方法：自定义比较大小的方法。  
2.因为引入的优先队列是基于自己写的最大堆实现的，最终前K个元素组成的最大堆中优先级最高，
但优先队列每次堆中最先被比较替换的是堆顶元素--本堆中最大值，而堆中其他元素优先级要高于该元素才对。  
所以要自定义堆顶最大值元素其优先级在最大堆中最低才行：最大堆中可比较值越大优先级越低，最大堆中可比较值越小优先级越高。  

- TopKFrequencyImprove.java  
使用java.util.PriorityQueue实现上述功能，体验和借鉴下更好的能力封装。  
java.util内置的优先队列，默认使用最小堆实现(前K最终个元素组成的最小堆中优先级最高)，且支持用户自定义Comparator比较器作为入参。   
优先队列每次堆中最先被比较替换的是堆顶元素--本堆中最小值，而堆中其他元素优先级要高于该元素。  
所以Comparator比较器(内部类使用Lambda表达式)中需重写Integer大小定义：Map中key对应的value(出现次数)越大优先级越高，key对应的value越小优先级越低。  
使用内部类还有一个好处是，可以直接操作内部的map对象，这样就可以直接通过key获取到value，在Comparator比较器中自定义直接比较大小(优先级)。  
做大小比较时，使用更优雅简洁的写法：(a, b) -> treeMap.get(a) - treeMap.get(b)。  

## 线段树(区间树)SegmentTree   
场景：区间数据在动态更新变化，不断查询区间数据。  
注：  
1.区间本身固定，仅仅是数据更新，不插入新数据。  
2.多次区间染色，后面一次允许覆盖上一次区间。  
例如2017年的用户至今消费情况，太空中某区域星球变化情况等。  
如果使用数组操作，更新和查询的时间复杂度都O(n)，使用线段树可使更新和查询的时间复杂度都为O(logn)。  
线段树是一种平衡二叉树。  
平衡二叉树：各个叶子节点的深度最多只差1层，不会退化成链表，可以使用数组做底层数据结构。  
完全二叉树是一种平衡二叉树，堆是一种完全二叉树，所以堆也是一种平衡二叉树。二分搜索树不一定是平衡二叉树。  

- SegmentTree.java，SegmentTreeTest.java      
1.底层使用静态数组实现线段树：  
将平衡二叉树结构的线段树，底层叶子节点null补全为满二叉树,那么
底层数组开辟4*n空间，确保能容纳底层叶子节点null补全为满二叉树的线段树所有元素。  
2.建立线段树，时间复杂度O(n)，定义一个内部私有方法buildSegmentTree：  
(1)递归结束条件，是最后的叶子节点left == right。     
(2)二分节点middle=left+(right - left)/2，注：写成这种形式可避免整数之和超出整数范围。  
(3)左子树的范围是前半段：buildSegmentTree(leftChildIndex, left, middle)。  
(4)右子树的范围是后半段：buildSegmentTree(rightChildIndex, middle+1, right)。  
(5)引入融合器，让调用者来实现：对两个子节点数据如何融合成父节点数据的业务逻辑。   
融合器Merger：定义Merger接口，作为SegmentTree构造方法的入参传入，调用者编写其业务逻辑。    
4.线段树查询，时间复杂度O(logn)，定义一个内部私有方法querySeg：  
querySeg(int curIndex, int left, int right, int queryLeft, int queryRight)，在curIndex为根节点的线段树\[left,right]的范围里，查询\[queryLeft，queryRight]的值。 
(1)递归结束条件，是最后的叶子节点queryLeft == left && queryRight == right。   
(2)如果要查询的左边界位于middle右侧queryLeft >= middle + 1，直接查右子树。   
(3)如果要查询的右边界位于middle左侧queryRight <= middle，直接查左子树。  
(4)如果要查询的结果两边子树都有，那就两边都查一下，然后放入调用者自定义的merger融合器计算结果返回。  
5.线段树更新某个节点的值，时间复杂度O(logn)，定义一个内部私有方法set：  
set(int curIndex, int left, int right, int index, E e)，在curIndex为根节点的线段树\[left,right]的范围里，将index位置的元素更新为e。  
(1)递归结束条件，是最后的叶子节点queryLeft == left && queryRight == right。   
(2)如果要更新的节点位于middle右侧queryLeft >= middle + 1，直接去右子树寻找。   
(3)如果要更新的节点位于middle左侧queryRight <= middle，直接去左子树寻找。  
(4)更新index元素值e后，需要调用融合器，重新计算下线段树的值tree\[curIndex] = merger.merge(tree\[leftChildIndex], tree\[rightChildIndex])。  

- NumsBySumArray.java    
Leetcode上有2道题目，序号303和307：给定一个整数数组nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。  
303题目，限定数组不用修改，考虑使用一个求和数组来做，初始化建立时间复杂度O(n)，查询的时间复杂度O(1)。  
但如果是307题目，声明数组需要支持更新，此时求和数组的更新的时间复杂度O(n)，超出了307算法时间复杂度要求。 

- NumsBySegmetTree.java    
303题目，限定数组不用修改，如果使用线段树来做，这样初始化建立时间复杂度O(n)，查询的时间复杂度O(logn)。  
307题目，声明数组需要支持更新，线段树的更新的时间复杂度O(logn)，满足307算法时间复杂度要求。 

## 字典树(前缀树)Trie     
场景：专门为处理字符串设计的数据结构/集合/映射，可做到查询每个条目的时间复杂度和字典中有多少个条目无关，只和查询字符长度w相关，而大多数单词长度<10。  
可以作为字符串集合set的底层数据结构，也可以作为字符串+value的映射map的底层数据结构。  
典型以空间换时间的数据结构，缺点是空间占用过大，为解决该问题，在此基础上有Compressed Trie、Ternary Search Trie。  

- Trie.java，TrieTest.java  
Trie作为字符串集合set的底层数据结构。  
1.Trie构造方法，由节点Node组成，每个Node元素：  
(1)到达该节点是否是单词的标识isWord：解决非叶子节点也可能是单词的问题，例如panda中，走到pan没到叶子节点也是一个单词。  
(2)下个字母和对应节点的映射TreeMap<Character, Node> next。  
2.向Trie中增加一个单词的方法addWord，时间复杂度O(w)，w是要增加的字符串长度，和待查询数据集大小无关：  
(1)遍历要增加的单词word的每个字符：  
如果Trie中没有该字符，创建字符+节点的映射Map，将当前节点cur移动到Trie中下个字符节点继续。 
(2)遍历完成后到达要插入单词的最后一个字符，做排重判断，如果这个单词之前不在Trie中：  
设置单词标识cur.isWord = true; Trie含单词数size++。  
3.精确匹配contains方法，查询Trie中是否包含某个单词word，时间复杂度O(w)，w是查询字符串长度，和待查询数据集无关：  
(1)遍历要精确匹配单词word的每个字符：  
如果到中间哪个字母已经找不到了，该单词肯定不在Trie中。 
如果单词该字母在Trie中找得到，那就移动到下一个Trie的Node节点继续匹配下个字母。  
(2)遍历完成后到达要插入单词的最后一个字符：  
如果单词到最后一个字母都匹配到了Trie树中的元素，且最后一个字母所在Trie树中的Node节点标识了有单词，才返回true，否则返回false。  
4.前缀匹配isPrefix方法，查询Trie中是否是否包含某个单词以prefix为前缀，时间复杂度O(w)，w是查询字符串长度，和待查询数据集无关：  
步骤与contains方法完全一样，区别只是最后一步： 
如果前缀prefix到最后一个字母都匹配到了Trie，前缀匹配完毕，Trie肯定存在单词以prefix为前缀。  
5.简单模式匹配search方法，搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母，重点是那个可匹配任意小写字母的点。  
(1)自定义内部方法match(Node node, String word, int index)，index是待搜索单词word中的字母索引，递归实现。  
(2)递归结束条件：已经到达word的最后一个字母。  
(3)递归过程：  
(3-1)如果要到trie树中查找的字母c是字母a~z：  
(3-1-1)如果单词挨字母在Trie树中找，到中间哪个字母已经找不到了，该单词肯定不在Trie中；  
(3-1-2)本次字母匹配成功，递归下个节点的值与word的下个字符继续看是否匹配。  
(3-2)如果要到trie树中查找的字母c是"."：  
(3-2-1)遍历trie树中当前节点的所有下个节点，每个下个节点的值与word的下个字符匹配，匹配成功返回true；  
(3-2-2)遍历trie树中当前节点的所有下个节点，与word的下个字符都没匹配成功，返回false。  

- MapSum.java，MapSumTest.java    
基于Trie的键值映射Map，leetcode的题目677：  
实现一个 MapSum 类里的两个方法，insert 和 sum。  
对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。  
对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。   
1.构造方法：定义一个内部类Node，这是Trie的一个元素；定义变量value用于记录单词权重，不为0表示到达该节点是个单词；定义TreeMap<Character, Node> next指向下个节点。  
2.插入单词insert方法：  
(1)遍历新单词word的每个字符，如果Trie中没有该字符，需要先创建节点。
(2)将当前节点cur移动到下个节点，移动到最后位置，新创建或在原有节点赋值新的val值。  
3.前缀匹配权重求和sum方法：
(1)如果前缀prefix挨字母在Trie中找，到中间哪个字母已经找不到了，该单词肯定不在Trie中，返回前缀匹配求和权重值为0。  
(2)前缀prefix该字母在Trie中找得到，那就移动到下一个Trie的Node节点继续匹配下个字母。  
(3)已经到达prefix的最后一个字母对应的节点，其本身以及其所有的next节点都匹配，value求和。  
(3-1)上述求和是个递归过程，递归结束条件：找到最终的叶子节点，直接返回自己的value值；    
(3-2)node本身命中，其value值肯定是求和的一部分；  
(3-3)遍历node的next节点，它们的value值也要加进来。  

## 并查集Union Find    
场景：用于解决2个元素是否有联系的问题，例如迷宫两点之间是否有连接，社交网络中2个用户，是否通过朋友的朋友之间有联系。  
底层可基于数组实现，元素值作为底层数组的索引值，元素归属的集合作为数组该索引对应的值。此时find的时间复杂度是O(1)，但union时要遍历整个数组，时间复杂度是O(n)。  
为提升union的速度，数组该索引对应的值改为存储其父节点的索引，形成子节点指向父节点的树形结构。    
让树的高度尽可能低，这样find和unionElements方法的效率都会提升。      

- UnionFind.java  
1.构造方法中的变量：   
(1)parent\[i]表示第i个元素所指向的父节点。  
(2)rank\[i]表示以i为根的集合所表示的树的层数的相对排名，不是树的height或者depth, 只是作为树高度比较的一个标准。
因为UnionFind本身不需要精确知道树的层数值，有层数相对排名值，作为unionElements时比较的标准就可以了。这样路径压缩的时候不用维护rank，避免额外计算开销。  
2.定义查找元素p所对应的集合编号内部私有方法find，O(h)复杂度, h为树的高度。    
使用路径压缩Path Compression，让树的高度越低，时间复杂度越低，性能越好。  
路径压缩方法1：  
(1)子节点p指向(parent\[p])父节点的父节点parent\[parent\[p]]，与父节点平级：parent\[p] = parent\[parent\[p]];   
(2)将p移动到父节点，也就是最开始的父节点的父节点位置，继续向根节点进发重复上述步骤，不断降低树的高度p = parent\[p];  
路径压缩方法2，递归算法：               
if(p != parent\[p]){parent\[p] = find2(parent\[p]);}     
不断将每个子节点指向根节点，形成根节点+N个子节点的2层高度树。  
3.判断元素p和元素q是否所属一个集合方法isConnected：find(p) == find(q)，O(h)复杂度, h为树的高度。  
4.合并元素p和元素q所属的集合方法unionElements，O(h)复杂度, h为树的高度：  
(1)分别找到元素p和元素q的根节点，如果相同，说明元素p和元素q本来就属于同一集合。  
(2)将高度低的树合并到高度高的树上，这样树的高度会更低，find和unionElements方法的效率都会提升。  
(3)高度低的树合并到高度高的树上，合并后树的高度，还是高度高的树的高度不变。    
(4)高度相同的2个树合并到其中一个树上，合并后树的高度+1。    

## AVL树   
二分搜索树有个缺陷：顺序增加元素会退化为链表，导致时间复杂度上升，性能下降。使用平衡二叉树来解决这个问题。    
平衡二叉树：对于任意一个节点，左子树和右子树的高度差的绝对值不能超过1。    
平衡因子：节点左子树和右子树的高度差，>1表示左左子树不平衡，<-1表示右子树不平衡。  

- AVLTree.java，AVLTreeTest.java    
在之前的二分搜索树BSTTree基础上，增加节点树高度height和平衡因子BalanceFactor，在add和remove方法上维护改进自平衡能力实现。  
1.增加一个变量height，进行二叉树各节点树高度维护。  
2.调用构造方法初始化时只有一个节点，树高度height=1。  
3.getBalanceFactor方法，获取节点node的平衡因子：  
空节点的平衡因子是0；非空节点的平衡因子是其左子树和右子树的高度差，>1表示左子树不平衡，<-1表示右子树不平衡。  
4.isBST方法：  
目的：AVL之后，树的结构有变动，需使用该方法判断该二叉树是否是二分搜索树。  
实现：调用中二叉树的递归中序遍历(左叶子节点->父节点->右叶子节点)，逐个将节点的key压入ArrayList，
从ArrayList依次取出之前压入的key，获取value输出应该是从小到大顺序排列的。    
5.isBalanced方法：  
目的：AVL之后，树的结构有变动，递归判断以node为根节点的二叉树是否是平衡二叉树。    
实现：(1)node节点是空节点，是平衡二叉树；  
(2)node节点左子树和右子树的高度差的绝对值超过1，不是平衡二叉树。     
(3)递归左子树和右子树，两者都是平衡二叉树node才可能是平衡二叉树。  
6.维护二叉树平衡且保持二分搜索树特性的关键方法：右旋方法rightRotate和左旋方法leftRotate。
旋转完成后，更新参与旋转的节点x和y的height，其树的高度=其左右子树更高的那个高度+1。    
7.add节点方法，增加平衡性维护功能：   
(1)向AVL树根节点增加元素后，当前节点node的树高度height=其左右子树更高的那个高度+1。  
(2)计算当前节点node的平衡因子，进行平衡维护：  
(2-1)插入的元素在不平衡节点左侧的左侧--LL，不平衡节点在node左子树 && 到node左子树节点看下，左子树节点的左子树高度更高时：  
node节点，右旋转可恢复树的平衡性，且保持二分搜索树特性。  
(2-2)插入的元素在不平衡节点右侧的右侧--RR，不平衡节点在node右子树 && 到node右子树节点看下，右子树节点的右子树高度更高时：  
node节点，左旋转可恢复树的平衡性，且保持二分搜索树特性。  
(2-3)插入的元素在不平衡节点左侧的右侧--LR，不平衡节点在node左子树 && 到node左子树节点看下，左子树节点的右子树高度更高时：  
先对node节点左子树节点左旋转，旋转后变成LL，再进行一次右旋转，可恢复树的平衡性，且保持二分搜索树特性。  
(2-4)插入的元素在不平衡节点右侧的左侧--RL，不平衡节点在node右子树 && 到node右子树节点看下，右子树节点的左子树高度更高时：  
先对node节点右子树节点右旋转，旋转后变成RR，再进行一次左旋转，可恢复树的平衡性，且保持二分搜索树特性。  
(2-5)如果不需要平衡性维护，直接返回node节点。  
8.remove节点方法，增加平衡性维护功能：  
(1)在二分搜索树remove节点基础上，不直接返回结果根节点，而是把结果根节点保存到一个变量returnNode。  
(2)对returnNode为根节点的树进行add节点方法中同样的左旋、右旋等判断和操作，维护成平衡二叉树+二分搜索树性质后再返回。  

## 红黑树RBTree      
红黑树是一种保持“黑平衡”的二叉树，2-3树是一种绝对平衡的树。  
红黑树背后的思想是用标准的二叉查找树（完全由2-结点构成）和一些额外的信息（替换3-结点）来表示2-3树。   
红黑树就是用红链接表示3-结点的2-3树，那么红黑树的插入、构造就可转化为2-3树的问题。   
我们将树中的链接分为两种类型：红链接将两个2-结点连接起来构成一个3-结点，黑链接则是2-3树中的普通链接。  
确切地说，将3-结点表示为由一条左斜的红色链接相连的两个2-结点，红节点向左倾斜。  
这种表示法的一个优点是：无需修改就可以直接使用标准二叉查找树的get()方法。 
对于任意的2-3树，只要对结点进行转换，都可以立即派生出一颗对应的二叉查找树。  
用这种方式表示2-3树的二叉查找树称为红黑树。   
AVL树使用场景：不变结构，元素查询。因为纯查询元素的性能，AVL树比红黑树高。  
红黑树使用场景：经常增加、删除元素，因为其增加和删除元素的性能，红黑树比AVL树高。   
红黑树的统计性能(综合增删改查所有操作)更优。Java中的有序TreeMap和有序TreeSet底层都使用的红黑树。  

RBTree.java，RBTreeTest.java       
1.节点增加颜色标识字段；默认构造方法创建的节点颜色初始为红色(向上融合)；空节点颜色为黑色。      
2.左旋leftRotate方法：x是节点node的右子树节点，最终x变为父节点返回。    
左旋转：node.right = x.left;  x.left = node;    
节点颜色改变： x.color = node.color;  node.color = RED;    
3.右旋rightRotate方法：x是节点node的左子树节点，最终x变为父节点返回。        
右旋转：node.left = x.right;  x.right = node;    
节点颜色改变： x.color = node.color;  node.color = RED;   
4.颜色翻转flipColors方法：节点自身置红，左右子节点置黑。    
5.保持根节点为黑节点：  
(1)红黑树为空的时候，新加红节点，因为红黑树的根节点要求是黑节点，红转黑。  
(2)红黑树不为空的时候，新加红节点，会逐步向父节点融合，有可能融合到根节点为红色，红转黑。    
6.对应向2-3树中的2节点增加一个元素，变成3节点的过程。  
(1)要增加的红节点小于父节点，放到父节点左侧(直接增加)。   
(2)要增加的红节点大于父节点，放到父节点右侧后，父节点左旋。   
7.对应向2-3树中的3节点增加一个元素：   
(1)向2-3树中的3节点增加一个更大的元素：先加到右子树，然后做颜色反转flipColors。      
(2)向2-3树中的3节点增加一个更小的元素：先加到左子树的左子树，然后左子树节点右旋转，然后做颜色反转flipColors。   
(3)向2-3树中的3节点增加一个中间大小的元素：先加到左子树的右子树，父节点的左子树节点左旋转，旋转后的左子树节点右旋转，然后做颜色反转flipColors。    
  
## 哈希表HashTable    
空间换时间的经典应用，但空间是有限的，如何节约空间又避免哈希冲突，哈希函数的设计极其重要。     
转成整型处理，是一种常用通用的哈希函数设计方法--基于数据集大小选取合适的素数取模，让“键”通过哈希函数得到的“索引”分布越均匀越好，冲突越少越好。  
实现时的小技巧：把幂转换为乘法降低计算量提升计算性能，把取模移进括号先算避免计算时整型溢出。  
原则：  
1.一致性：如果a==b，则hash(a)==hash(b)。  
2.高效性：计算高效简便。     
3.均匀性：哈希值均匀分布。   

- FirstUniqChar.java，FirstUniqCharTest.java    
leetcode上的题目387：给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。假定该字符串只包含小写字母。  
因为字符串限定只包含小写字母，考虑使用26个元素的数组构建Hash表实现：  
1.将字符串的每个字符映射到数组中，遇到重复字母，对应索引的数组数值+1。  
2.再次依次遍历字符串的每个字符，看哪个字符数组中数值先出现1，返回索引；遍历到最后一个字符也没找到，返回-1。   

- Person.java，PersonTest.java  
Java默认使用对象的引用地址做hashCode，比较的是引用地址，new出来2个对象，hashCode一定不同也就一定不等。使用HashSet或HashMap时一定需注意。    
1.public int hashCode()方法：自定义重写Person对象的hashCode方法。忽略字符大小写，对值进行哈希。     
2.public boolean equals(Object o)方法：自定义重写Person对象的equals方法。如果2个对象hashCode相同(哈希冲突)时，再进行值比较看看是否真的相同。  
(1)如果要比较的对象是null，无需比较，直接返回不同。      
(2)如果引用地址相同，肯定是相同的对象。  
(3)如果要比较的2个对象类不同，直接返回不同。  
(4)如果上述情况都不是，入参Object强制类型转换为Person对象，对每个属性逐个比较。  

- HashTable.java  
1.自定义了一个哈希表数据结构：简单起见，直接使用TreeMap数组做自定义哈希表每个元素的底层数据结构；
哈希冲突的解决方法，使用了常用的封闭地址的链地址法Seperate Chaining。  
2.哈希函数的设计，使用模一个素数M的方法。  
3.素数M的选取问题：定义一个素数M的数组capacity。数学家给出的多大数据量下，采用哪个素数M，
会让哈希冲突问题更少。https://planetmath.org/goodhashtableprimes。   
4.自定义哈希方法：java的hashCode结果是有符号int类型(支持负数)，为了做数组索引，取绝对值(& 0x7fffffff)，然后素数取模。  
5.resize方法：用于扩容缩容时，改变哈希表大小。  
(1)先建立一个大小为newM，元素为TreeMap的数组。  
(2)遍历原先大小为M的哈希表，遍历记录原先哈希表中的元素--获取到每个TreeMap。  
(3)遍历该TreeMap，获取并记录每个值到新哈希表中，注意，此时哈希的M是newM，而不是oldM。  
(4)完成老哈希表到新哈希表的赋值后，将hashTable引用指向新哈希表。  
5.add方法：向哈希表中增加元素(key,value)。  
(1)如果hashTable中数组索引已存在key的hash值，且其chain(TreeMap)中的确存在该key值，修改value。  
(2)如果hashTable中不存在该key，添加到hashTable中，size+1。  
(3)平均哈希冲突N/M大于EXTEND_THREAD时，哈希表扩容到capacity数组中下一个更大的素数值，注意防数组越界。  
6.remove方法：从哈希表中删除键为key的元素，返回其value。  
(1)如果hashTable中数组索引已存在key的hash值，且其chain(TreeMap)中的确存在该key值，删除该元素并记录value返回，size-1。  
(2)平均哈希冲突N/M小于SHRINK_THREAD时，哈希表缩容到capacity数组中上一个更小的素数值，注意防数组越界。  
7.set方法：修改哈希表中键为key的元素的值为value。  
(1)如果hashTable中不存在key，报错无法set。  
(2)如果hashTable中存在key，修改该元素的值为value。  
8.contains方法：判断哈希表中是否存在key，hashTable\[hash(key)].containsKey(key)。    
9.get方法：获取哈希表中指定key的value，hashTable\[hash(key)].get(key)。  

## 排序Sort   
-  BubbleSort.java, BubbleSortTest.java   
冒泡排序：交换排序类算法，算法平均复杂度O(n²)，稳定，支持原地排序。  
1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。  
2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。   
3.针对所有的元素重复以上的步骤，除了最后一个。  
4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。  
性能提升的技巧是设定一个标记bSwap，若为false，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。   

-  QuickSort.java, QuickSortTest.java   
快速排序：交换排序类算法，算法平均复杂度O(nlogn)，不稳定，支持原地排序。数据量大时排序才有优势。      
1.从数组中挑出一个元素，例如第1个元素，作为 “基准值std”。  
2.挖坑分治法：比基准值std小的向左移动，比基准值std大的向右移动,一直到左右index重合(此位置leftIndex==rightIndex)，把标准值std放到该index上。   
3.基准值左右两侧继续递归重复上述过程，一直到左右两侧index重合。   

-  InsertSort.java, InsertSortTest.java   
插入排序：交换排序类算法，算法平均复杂度O(n²)，稳定，支持原地排序。 
1.首先设定插入次数，即循环次数，for(int i=1;i<length;i++)，从第2个数开始~最后一个数，逐个与左侧有序数组数值比较。   
2.设定本次循环的待插入数insertNum=a\[i]，以及得到左侧有序数组的最后一个数的index，j=i-1。   
3.从左侧有序数组最后一个数arr\[j]开始向前遍历j--，如果待插入数insertNum小于当前值arr\[j]，就将当前值arr\[j]向后移动一位a\[j + 1] = a\[j]。   
4.如果待插入数insertNum>=当前值arr\[j]，将当前数放到空着的位置，即a\[j + 1] = insertNum。   


